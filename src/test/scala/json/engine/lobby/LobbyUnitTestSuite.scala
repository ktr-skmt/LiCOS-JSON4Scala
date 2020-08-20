package json.engine.lobby

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.engine.LobbyUnitTestExample
import json.engine.lobby.unitTestExample.{
  HostPlayer,
  Human,
  PingResult,
  PlayerInWaitingPage,
  PlayerSetting,
  PlayerTokenInKickOutPlayer,
  Robot,
  RobotPlayerInfo,
  RoleSetting,
  SubAvatarInfo,
  Support,
  SupportedComposition
}
import json.parser.LobbyUnitTestParser
import org.scalatest.{FunSuite, Matchers}
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import play.api.libs.json.{JsValue, Json}

import scala.io.{Codec, Source}

final class LobbyUnitTestSuite extends FunSuite with Matchers with TableDrivenPropertyChecks with LobbyUnitTestParser {

  private val fractions: TableFor1[LobbyUnitTestExample] =
    Table(
      "jsonExample",
      HostPlayer("hostPlayer.json"),
      Human("human.json"),
      PingResult("pingResult.json"),
      PlayerTokenInKickOutPlayer("playerInKickOutPlayer.json"),
      PlayerInWaitingPage("playerInWaitingPage.json"),
      Robot("robot.json"),
      RoleSetting("roleSetting.json"),
      PlayerSetting("playerSetting.json"),
      SupportedComposition("supportedComposition.json"),
      Support("support.json"),
      SubAvatarInfo("subAvatarInfo.json"),
      RobotPlayerInfo("robotPlayerInfo.json")
    )

  private val log: Logger = Logger[LobbyUnitTestSuite]

  test("json.lobbyUnitTestSuite") {
    forEvery(fractions) { jsonExample: LobbyUnitTestExample =>
      val jsonType:       String = jsonExample.`type`
      val url:            String = jsonExample.path
      implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
      log.info(url)
      val source = Source.fromURL(url)
      val msg: String = source.getLines.mkString("\n")
      source.close()
      log.debug(msg)
      val json: JsValue = Json.parse(msg)

      jsonType match {
        case "unitTest/HostPlayer" =>
          parseHostPlayer(json).nonEmpty shouldBe true
        case "unitTest/Human" =>
          parseHuman(json).nonEmpty shouldBe true
        case "unitTest/PingResult" =>
          parsePingResult(json).nonEmpty shouldBe true
        case "unitTest/PlayerInKickOutPlayer" =>
          parsePlayerTokenInKickOutPlayer(json).nonEmpty shouldBe true
        case "unitTest/PlayerInWaitingPage" =>
          parsePlayerInWaitingPage(json).nonEmpty shouldBe true
        case "unitTest/PlayerSetting" =>
          parsePlayerSetting(json).nonEmpty shouldBe true
        case "unitTest/Robot" =>
          parseRobot(json).nonEmpty shouldBe true
        case "unitTest/RoleSetting" =>
          parseRoleSetting(json).nonEmpty shouldBe true
        case "unitTest/SupportedComposition" =>
          parseSupportedComposition(json).nonEmpty shouldBe true
        case "unitTest/Support" =>
          parseSupport(json).nonEmpty shouldBe true
        case "unitTest/SubAvatarInfo" =>
          parseSubAvatarInfo(json).nonEmpty shouldBe true
        case "unitTest/RobotPlayerInfo" =>
          parseRobotPlayerInfo(json).nonEmpty shouldBe true
        case _ =>
          fail("no json type")
      }
    }
  }

}
