package sample

import java.net.URL

import licos.entity.{HostPlayer, VillageInfoFromLobby}
import licos.knowledge.{Cast, HumanArchitecture, HumanPlayerLobby, RandomAvatarSetting}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.engine.processing.village.{VillageBOX, VillageProcessingEngine, VillageProcessingEngineFactory}
import licos.protocol.engine.processing.{SpecificProcessingEngineFactory, VillagePE}
import play.api.libs.json.{JsValue, Json}
import protocol.engine.village.VillageBox

import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success}

object ProtocolVillageMessageRunner extends App {
  private val processingEngineFactory: VillageProcessingEngineFactory =
    SpecificProcessingEngineFactory.create(VillagePE).asInstanceOf[VillageProcessingEngineFactory]

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create
  // set analysis engines
  private val aJSONExampleOfTheLiCOSProtocol: String = {
    val source: BufferedSource = Source.fromURL(
      "https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/village/example/0.3/server2client/firstMorning.jsonld"
    )
    val json: String = source.getLines().mkString("\n")
    source.close()
    json
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
    Cast.playerNumRoleNumMap(15)("A"),
    1,
    RandomAvatarSetting,
    15,
    None,
    "Christopher",
    new URL("https://werewolf.world/image/0.3/character_icons/50x50/a_50x50.png")
  )

  val anExampleOfBOX: VillageBOX = new VillageBox(villageInfoFromLobby)

  processingEngine.process(anExampleOfBOX, aJSONExampleOfTheLiCOSProtocol) match {
    case Success(protocol: VillageMessageProtocol) =>
      protocol.toJsonOpt foreach { json: JsValue =>
        System.err.println(Json.prettyPrint(json))
      }
    case Failure(exception: Throwable) =>
      System.err.println(exception.getMessage)
  }
}
