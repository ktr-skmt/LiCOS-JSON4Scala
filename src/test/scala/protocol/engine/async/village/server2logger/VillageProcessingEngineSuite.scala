package protocol.engine.async.village.server2logger

import java.net.URL
import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import licos.entity.{HostPlayer, VillageInfoFromLobby}
import licos.json2protocol.village.server2logger.Json2VillageMessageProtocol
import licos.knowledge.{Composition, HumanArchitecture, HumanPlayerLobby, RandomAvatarSetting}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.engine.async.processing.{SpecificProcessingEngineFactory, VillagePE4Logger}
import licos.protocol.engine.async.processing.village.server2logger.{
  VillageProcessingEngine4Logger,
  VillageProcessingEngineFactory4Logger
}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import play.api.libs.json.Json
import protocol.element.VillageMessageTestProtocol
import protocol.engine.VillageExample
import protocol.engine.village.VillageBox
import protocol.engine.async.village.analysis.client2server.server2logger.VoteAE
import protocol.engine.async.village.analysis.server2client.server2logger.{ChatFromServerAE, MorningPhaseAE}
import protocol.engine.village.example.client2server.server2logger.Vote
import protocol.engine.village.example.server2client.server2logger.{ChatFromServer, MorningPhase}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.{Codec, Source}

final class VillageProcessingEngineSuite extends AnyFunSuite with Matchers with TableDrivenPropertyChecks {

  private val fractions: TableFor1[VillageExample] =
    Table(
      "jsonExample",
      Vote("nightVoteForLog.jsonld"),
      ChatFromServer("myMessageOnChatForLog.jsonld"),
      MorningPhase("morningForLog.jsonld")
    )

  private val log: Logger = Logger[VillageProcessingEngineSuite]

  private val processingEngineFactory: VillageProcessingEngineFactory4Logger = SpecificProcessingEngineFactory
    .create(VillagePE4Logger)
    .asInstanceOf[VillageProcessingEngineFactory4Logger]
    .set(new VoteAE())
    .set(new ChatFromServerAE())
    .set(new MorningPhaseAE())

  private val processingEngine: VillageProcessingEngine4Logger = processingEngineFactory.create

  test("protocol.async.villageProcessingEngineSuite") {
    forEvery(fractions) { jsonExample: VillageExample =>
      val hostPlayer = HostPlayer(
        1L,
        "Anonymous",
        isAnonymous = true,
        HumanArchitecture
      )
      val villageInfoFromLobby = VillageInfoFromLobby(
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

      val box = new VillageBox(villageInfoFromLobby)

      val jsonType:       String = jsonExample.`type`
      val url:            URL    = jsonExample.path
      implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
      log.info(url.toString)
      val source = Source.fromURL(url)
      val msg: String = source.getLines().mkString("\n")
      source.close()
      log.debug(msg)

      import scala.concurrent.ExecutionContext.Implicits.global

      Json2VillageMessageProtocol.toProtocolOpt(Json.parse(msg), villageInfoFromLobby) match {
        case Some(protocol: VillageMessageProtocol) =>
          Await.ready(
            processingEngine
              .process(box, protocol)
              .map { messageProtocol: VillageMessageProtocol =>
                messageProtocol match {
                  case p: VillageMessageTestProtocol =>
                    p.text shouldBe jsonType
                  case _ =>
                    fail("No VillageMessageTestProtocol")
                }
              }
              .recover { case error: Throwable =>
                fail(
                  List[String](
                    "No response is generated.",
                    error.getMessage,
                    msg
                  ).mkString("\n")
                )
              },
            Duration.Inf
          )
        case _ =>
          fail("No protocol")
      }
    }
  }
}
