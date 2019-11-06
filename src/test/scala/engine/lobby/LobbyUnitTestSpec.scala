package engine.lobby

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import engine.LobbyUnitTestExample
import engine.lobby.unitTestExample.{
  HostPlayer,
  Human,
  PingResult,
  PlayerInWaitingPage,
  PlayerSetting,
  PlayerTokenInKickOutPlayer,
  Robot,
  RoleSetting
}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import parser.LobbyUnitTestParser
import play.api.libs.json.{JsValue, Json}

import scala.io.{Codec, Source}

object LobbyUnitTestSpec {

  @DataPoints
  def exampleSeq: Array[LobbyUnitTestExample] = Array[LobbyUnitTestExample](
    HostPlayer("hostPlayer.json"),
    Human("human.json"),
    PingResult("pingResult.json"),
    PlayerTokenInKickOutPlayer("playerInKickOutPlayer.json"),
    PlayerInWaitingPage("playerInWaitingPage.json"),
    Robot("robot.json"),
    RoleSetting("roleSetting.json"),
    PlayerSetting("playerSetting.json")
  )
}

@RunWith(classOf[Theories])
class LobbyUnitTestSpec extends AssertionsForJUnit with LobbyUnitTestParser {

  private final val logger: Logger = Logger[LobbyUnitTestSpec]

  @Theory
  def process(jsonExample: LobbyUnitTestExample): Unit = {
    val jsonType:       String = jsonExample.`type`
    val url:            String = jsonExample.path
    implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
    logger.info(url)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    logger.debug(msg)
    val json: JsValue = Json.parse(msg)

    jsonType match {
      case "unitTest/HostPlayer" =>
        assert(parseHostPlayer(json).nonEmpty)
      case "unitTest/Human" =>
        assert(parseHuman(json).nonEmpty)
      case "unitTest/PingResult" =>
        assert(parsePingResult(json).nonEmpty)
      case "unitTest/PlayerInKickOutPlayer" =>
        assert(parsePlayerTokenInKickOutPlayer(json).nonEmpty)
      case "unitTest/PlayerInWaitingPage" =>
        assert(parsePlayerInWaitingPage(json).nonEmpty)
      case "unitTest/PlayerSetting" =>
        assert(parsePlayerSetting(json).nonEmpty)
      case "unitTest/Robot" =>
        assert(parseRobot(json).nonEmpty)
      case "unitTest/RoleSetting" =>
        assert(parseRoleSetting(json).nonEmpty)
      case _ =>
        fail("no json type")
    }
  }
}
