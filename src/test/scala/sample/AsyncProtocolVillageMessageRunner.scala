package sample

import java.net.URL

import licos.entity.{HostPlayer, VillageInfoFromLobby}
import licos.json2protocol.village.Json2VillageMessageProtocol
import licos.knowledge.{Composition, HumanArchitecture, HumanPlayerLobby, RandomAvatarSetting}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.engine.async.processing.village.{VillageProcessingEngine, VillageProcessingEngineFactory}
import licos.protocol.engine.async.processing.{SpecificProcessingEngineFactory, VillagePE}
import licos.protocol.engine.processing.village.VillageBOX
import play.api.libs.json.{JsValue, Json}
import protocol.engine.async.village.analysis.server2client.FirstMorningPhaseAE
import protocol.engine.village.VillageBox

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.{BufferedSource, Source}

object AsyncProtocolVillageMessageRunner extends App {
  private val processingEngineFactory: VillageProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(VillagePE)
    .asInstanceOf[VillageProcessingEngineFactory]
    .set(new FirstMorningPhaseAE())

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create

  private val aJSONExampleOfTheLiCOSProtocol: JsValue = {
    val source: BufferedSource = Source.fromURL(
      "https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/village/example/0.3/server2client/firstMorning.jsonld"
    )
    val json: String = source.getLines().mkString("\n")
    source.close()
    Json.parse(json)
  }

  private val hostPlayer = HostPlayer(
    1L,
    "Anonymous",
    isAnonymous = true,
    HumanArchitecture
  )
  private val villageInfoFromLobby = VillageInfoFromLobby(
    HumanPlayerLobby,
    hostPlayer,
    Composition.support.`for`(15).A,
    1,
    RandomAvatarSetting,
    15,
    Option.empty[String],
    "Christopher",
    new URL("https://werewolf.world/image/0.3/character_icons/50x50/a_50x50.png")
  )

  private val anExampleOfBOX: VillageBOX = new VillageBox(villageInfoFromLobby)

  import scala.concurrent.ExecutionContext.Implicits.global

  Json2VillageMessageProtocol.toProtocolOpt(aJSONExampleOfTheLiCOSProtocol, villageInfoFromLobby) match {
    case Some(protocol: VillageMessageProtocol) =>
      Await.ready(
        processingEngine
          .process(anExampleOfBOX, protocol)
          .map { messageProtocol: VillageMessageProtocol =>
            messageProtocol.toJsonOpt.foreach { json: JsValue =>
              System.err.println(Json.prettyPrint(json))
            }
          }
          .recover {
            case error: Throwable =>
              System.err.println(error.getMessage)
          },
        Duration.Inf
      )
    case _ =>
      System.err.println("No protocol")
  }

}
