package protocol.engine.village.server2logger

import java.nio.charset.StandardCharsets
import java.util.Locale

import com.typesafe.scalalogging.Logger
import licos.entity.{AvatarInVillage, HostPlayer, Village, VillageFactory}
import licos.json.parser.VillageParser
import licos.knowledge.{Cast, HumanArchitecture, HumanPlayerLobby, Morning, Night, Noon, RandomAvatarSetting}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.engine.processing.{SpecificProcessingEngineFactory, VillagePE4Logger}
import licos.protocol.engine.processing.server2logger.{
  VillageProcessingEngine4Logger,
  VillageProcessingEngineFactory4Logger
}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import protocol.element.VillageMessageTestProtocol
import protocol.engine.VillageExample
import protocol.engine.village.VillageBox
import protocol.engine.village.analysis.client2server.server2logger.VoteAE
import protocol.engine.village.analysis.server2client.server2logger.{ChatFromServerAE, MorningPhaseAE}
import protocol.engine.village.example.client2server.server2logger.Vote
import protocol.engine.village.example.server2client.{FirstMorningPhase, NightPhase, NoonPhase}
import protocol.engine.village.example.server2client.server2logger.{ChatFromServer, MorningPhase}

import scala.collection.mutable.ListBuffer
import scala.io.{Codec, Source}
import scala.util.{Failure, Success}

object VillageProcessingEngineSpec {
  @DataPoints
  def exampleSeq: Array[VillageExample] = Array[VillageExample](
    Vote("nightVoteForLog.jsonld"),
    ChatFromServer("myMessageOnChatForLog.jsonld"),
    MorningPhase("morningForLog.jsonld")
  )
}

@RunWith(classOf[Theories])
class VillageProcessingEngineSpec extends AssertionsForJUnit with VillageParser {

  private final val log: Logger = Logger[VillageProcessingEngineSpec]

  private val processingEngineFactory: VillageProcessingEngineFactory4Logger = SpecificProcessingEngineFactory
    .create(VillagePE4Logger)
    .asInstanceOf[VillageProcessingEngineFactory4Logger]
    .set(new VoteAE())
    .set(new ChatFromServerAE())
    .set(new MorningPhaseAE())

  private val processingEngine: VillageProcessingEngine4Logger = processingEngineFactory.create
  @Theory
  def process(jsonExample: VillageExample): Unit = {
    val hostPlayer = HostPlayer(
      1L,
      "Christopher",
      isAnonymous = true,
      HumanArchitecture
    )
    val avatars = ListBuffer.empty[AvatarInVillage]
    val cast: Cast = Cast.playerNumRoleNumMap(15)("A")
    val villageFactory = new VillageFactory()
      .setId(1L)
      .setName("Fearwick")
      .setLobby(HumanPlayerLobby)
      .setHostPlayer(hostPlayer)
      .setAvatarSetting(RandomAvatarSetting)
      .setMaxNumberOfHumanPlayers(15)
      .setMaxNumberOfChatMessages(10)
      .setMaxLengthOfUnicodeCodePoints(140)
      .setLanguage(Locale.JAPANESE)
      .setStory(1)
      .setIdForSearching(1)
      .setCast(cast)
      .setAvatars(avatars)

    villageFactory.create match {
      case Success(village: Village) =>
        val box = new VillageBox(village)

        val jsonType:       String = jsonExample.`type`
        val url:            String = jsonExample.path
        implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
        log.info(url)
        val source = Source.fromURL(url)
        val msg: String = source.getLines.mkString("\n")
        source.close()
        log.debug(msg)
        jsonType match {
          case FirstMorningPhase.`type` =>
            box.village.currentPhase = Morning
            box.village.currentDay   = 1
          case MorningPhase.`type` =>
            box.village.currentPhase = Morning
          case NoonPhase.`type` =>
            box.village.currentPhase = Noon
          case NightPhase.`type` =>
            box.village.currentPhase = Night
          case _ =>
          // Do nothing
        }
        processingEngine.process(box, msg) match {
          case Success(protocol: VillageMessageProtocol) =>
            protocol match {
              case p: VillageMessageTestProtocol =>
                assert(p.text == jsonType)
              case _ =>
                fail(
                  Seq[String](
                    "No AuthMessageTestProtocol"
                  ).mkString("\n")
                )
            }
          case Failure(error: Throwable) =>
            fail(
              Seq[String](
                "No response is generated.",
                error.getMessage,
                msg
              ).mkString("\n")
            )
        }
      case Failure(exception: Exception) =>
    }

  }
}
