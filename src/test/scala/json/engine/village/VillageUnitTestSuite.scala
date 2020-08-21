package json.engine.village

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.engine.VillageUnitTestExample
import json.engine.village.unitTestExample.{
  Avatar,
  Base,
  BoardResult,
  ChatSettings,
  ChatText,
  Name,
  StarInfo,
  SubAnonymousAudienceChat,
  SubBoard,
  SubChatFromClient,
  SubChatFromServer,
  SubErrorFromClient,
  SubErrorFromServer,
  SubFlavorText,
  SubGameResult,
  SubOnymousAudienceBoard,
  SubOnymousAudienceChat,
  SubOnymousAudienceScroll,
  SubPhase,
  SubScroll,
  SubStar,
  SubVote,
  Update,
  Village,
  VotingResultDetail,
  VotingResultSummary
}
import json.engine.village.unitTestExample.character.{
  Character,
  ResultCharacter,
  RoleCharacter,
  SimpleCharacter,
  StatusCharacter
}
import json.engine.village.unitTestExample.role.{ResultRole, Role, SimpleRole}
import json.parser.VillageUnitTestParser
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import play.api.libs.json.{JsValue, Json}

import scala.io.{Codec, Source}

final class VillageUnitTestSuite
    extends AnyFunSuite
    with Matchers
    with TableDrivenPropertyChecks
    with VillageUnitTestParser {

  private val fractions: TableFor1[VillageUnitTestExample] =
    Table(
      "jsonExample",
      Character("character/character.json"),
      ResultCharacter("character/resultCharacter.json"),
      RoleCharacter("character/roleCharacter.json"),
      SimpleCharacter("character/simpleCharacter.json"),
      StatusCharacter("character/statusCharacter.json"),
      ResultRole("role/resultRole.json"),
      Role("role/role.json"),
      SimpleRole("role/simpleRole.json"),
      Avatar("avatar.json"),
      BoardResult("boardResult.json"),
      ChatSettings("chatSettings.json"),
      ChatText("chatText.json"),
      Name("name.json"),
      StarInfo("starInfo.json"),
      Update("update.json"),
      SubOnymousAudienceBoard("subOnymousAudienceBoard.json"),
      SubAnonymousAudienceChat("subAnonymousAudienceChat.json"),
      SubOnymousAudienceChat("subOnymousAudienceChat.json"),
      SubOnymousAudienceScroll("subOnymousAudienceScroll.json"),
      SubBoard("subBoard.json"),
      SubErrorFromClient("subErrorFromClient.json"),
      SubErrorFromServer("subErrorFromServer.json"),
      SubPhase("subPhase.json"),
      SubScroll("subScroll.json"),
      SubStar("subStar.json"),
      SubVote("subVote.json"),
      VotingResultDetail("votingResultDetail.json"),
      VotingResultSummary("votingResultSummary.json"),
      Village("village.json"),
      SubChatFromClient("subChatFromClient.json"),
      SubChatFromServer("subChatFromServer.json"),
      SubFlavorText("subFlavorText.json"),
      SubGameResult("subGameResult.json"),
      Base("base.json")
    )

  private val log: Logger = Logger[VillageUnitTestSuite]

  test("json.villageUnitTestSuite") {
    forEvery(fractions) { jsonExample: VillageUnitTestExample =>
      val jsonType:       String = jsonExample.`type`
      val url:            String = jsonExample.path
      implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
      log.info(url)
      val source = Source.fromURL(url)
      val msg: String = source.getLines().mkString("\n")
      source.close()
      log.debug(msg)
      val json: JsValue = Json.parse(msg)

      jsonType match {
        case "unitTest/Character" =>
          parseCharacter(json).nonEmpty shouldBe true
        case "unitTest/ResultCharacter" =>
          parseResultCharacter(json).nonEmpty shouldBe true
        case "unitTest/RoleCharacter" =>
          parseRoleCharacter(json).nonEmpty shouldBe true
        case "unitTest/SimpleCharacter" =>
          parseSimpleCharacter(json).nonEmpty shouldBe true
        case "unitTest/StatusCharacter" =>
          parseStatusCharacter(json).nonEmpty shouldBe true
        case "unitTest/ResultRole" =>
          parseResultRole(json).nonEmpty shouldBe true
        case "unitTest/Role" =>
          parseRole(json).nonEmpty shouldBe true
        case "unitTest/SimpleRole" =>
          parseSimpleRole(json).nonEmpty shouldBe true
        case "unitTest/Avatar" =>
          parseAvatar(json).nonEmpty shouldBe true
        case "unitTest/Base" =>
          parseBase(json).nonEmpty shouldBe true
        case "unitTest/BoardResult" =>
          parseBoardResult(json).nonEmpty shouldBe true
        case "unitTest/ChatSettings" =>
          parseChatSettings(json).nonEmpty shouldBe true
        case "unitTest/ChatText" =>
          parseChatText(json).nonEmpty shouldBe true
        case "unitTest/Name" =>
          parseName(json).nonEmpty shouldBe true
        case "unitTest/StarInfo" =>
          parseStarInfo(json).nonEmpty shouldBe true
        case "unitTest/SubOnymousAudienceBoard" =>
          parseSubOnymousAudienceBoard(json).nonEmpty shouldBe true
        case "unitTest/SubAnonymousAudienceChat" =>
          parseSubAnonymousAudienceChat(json).nonEmpty shouldBe true
        case "unitTest/SubOnymousAudienceChat" =>
          parseSubOnymousAudienceChat(json).nonEmpty shouldBe true
        case "unitTest/SubOnymousAudienceScroll" =>
          parseSubOnymousAudienceScroll(json).nonEmpty shouldBe true
        case "unitTest/SubBoard" =>
          parseSubBoard(json).nonEmpty shouldBe true
        case "unitTest/SubChatFromClient" =>
          parseSubChatFromClient(json).nonEmpty shouldBe true
        case "unitTest/SubChatFromServer" =>
          parseSubChatFromServer(json).nonEmpty shouldBe true
        case "unitTest/SubErrorFromClient" =>
          parseSubErrorFromClient(json).nonEmpty shouldBe true
        case "unitTest/SubErrorFromServer" =>
          parseSubErrorFromServer(json).nonEmpty shouldBe true
        case "unitTest/SubFlavorText" =>
          parseSubFlavorText(json).nonEmpty shouldBe true
        case "unitTest/SubGameResult" =>
          parseSubGameResult(json).nonEmpty shouldBe true
        case "unitTest/SubPhase" =>
          parseSubPhase(json).nonEmpty shouldBe true
        case "unitTest/SubScroll" =>
          parseSubScroll(json).nonEmpty shouldBe true
        case "unitTest/SubStar" =>
          parseSubStar(json).nonEmpty shouldBe true
        case "unitTest/SubVote" =>
          parseSubVote(json).nonEmpty shouldBe true
        case "unitTest/Update" =>
          parseUpdate(json).nonEmpty shouldBe true
        case "unitTest/Village" =>
          parseVillage(json).nonEmpty shouldBe true
        case "unitTest/VotingResultDetail" =>
          parseVotingResultDetail(json).nonEmpty shouldBe true
        case "unitTest/VotingResultSummary" =>
          parseVotingResultSummary(json).nonEmpty shouldBe true
        case _ =>
          fail("no json type")
      }
    }
  }

}
