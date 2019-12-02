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
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import json.parser.VillageUnitTestParser
import play.api.libs.json.{JsValue, Json}

import scala.io.{Codec, Source}

object VillageUnitTestSuite {

  @DataPoints
  def exampleSeq: Array[VillageUnitTestExample] = Array[VillageUnitTestExample](
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
}

@RunWith(classOf[Theories])
class VillageUnitTestSuite extends AssertionsForJUnit with VillageUnitTestParser {

  private final val log: Logger = Logger[VillageUnitTestSuite]

  @Theory
  def process(jsonExample: VillageUnitTestExample): Unit = {
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
      case "unitTest/Character" =>
        assert(parseCharacter(json).nonEmpty)
      case "unitTest/ResultCharacter" =>
        assert(parseResultCharacter(json).nonEmpty)
      case "unitTest/RoleCharacter" =>
        assert(parseRoleCharacter(json).nonEmpty)
      case "unitTest/SimpleCharacter" =>
        assert(parseSimpleCharacter(json).nonEmpty)
      case "unitTest/StatusCharacter" =>
        assert(parseStatusCharacter(json).nonEmpty)
      case "unitTest/ResultRole" =>
        assert(parseResultRole(json).nonEmpty)
      case "unitTest/Role" =>
        assert(parseRole(json).nonEmpty)
      case "unitTest/SimpleRole" =>
        assert(parseSimpleRole(json).nonEmpty)
      case "unitTest/Avatar" =>
        assert(parseAvatar(json).nonEmpty)
      case "unitTest/Base" =>
        assert(parseBase(json).nonEmpty)
      case "unitTest/BoardResult" =>
        assert(parseBoardResult(json).nonEmpty)
      case "unitTest/ChatSettings" =>
        assert(parseChatSettings(json).nonEmpty)
      case "unitTest/ChatText" =>
        assert(parseChatText(json).nonEmpty)
      case "unitTest/Name" =>
        assert(parseName(json).nonEmpty)
      case "unitTest/StarInfo" =>
        assert(parseStarInfo(json).nonEmpty)
      case "unitTest/SubOnymousAudienceBoard" =>
        assert(parseSubOnymousAudienceBoard(json).nonEmpty)
      case "unitTest/SubAnonymousAudienceChat" =>
        assert(parseSubAnonymousAudienceChat(json).nonEmpty)
      case "unitTest/SubOnymousAudienceChat" =>
        assert(parseSubOnymousAudienceChat(json).nonEmpty)
      case "unitTest/SubOnymousAudienceScroll" =>
        assert(parseSubOnymousAudienceScroll(json).nonEmpty)
      case "unitTest/SubBoard" =>
        assert(parseSubBoard(json).nonEmpty)
      case "unitTest/SubChatFromClient" =>
        assert(parseSubChatFromClient(json).nonEmpty)
      case "unitTest/SubChatFromServer" =>
        assert(parseSubChatFromServer(json).nonEmpty)
      case "unitTest/SubErrorFromClient" =>
        assert(parseSubErrorFromClient(json).nonEmpty)
      case "unitTest/SubErrorFromServer" =>
        assert(parseSubErrorFromServer(json).nonEmpty)
      case "unitTest/SubFlavorText" =>
        assert(parseSubFlavorText(json).nonEmpty)
      case "unitTest/SubGameResult" =>
        assert(parseSubGameResult(json).nonEmpty)
      case "unitTest/SubPhase" =>
        assert(parseSubPhase(json).nonEmpty)
      case "unitTest/SubScroll" =>
        assert(parseSubScroll(json).nonEmpty)
      case "unitTest/SubStar" =>
        assert(parseSubStar(json).nonEmpty)
      case "unitTest/SubVote" =>
        assert(parseSubVote(json).nonEmpty)
      case "unitTest/Update" =>
        assert(parseUpdate(json).nonEmpty)
      case "unitTest/Village" =>
        assert(parseVillage(json).nonEmpty)
      case "unitTest/VotingResultDetail" =>
        assert(parseVotingResultDetail(json).nonEmpty)
      case "unitTest/VotingResultSummary" =>
        assert(parseVotingResultSummary(json).nonEmpty)
      case _ =>
        fail("no json type")
    }
  }
}
