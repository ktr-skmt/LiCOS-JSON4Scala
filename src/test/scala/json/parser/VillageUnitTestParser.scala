package json.parser

import licos.json.element.village.{
  JsonAvatar,
  JsonBase,
  JsonBoardResult,
  JsonChatSettings,
  JsonChatText,
  JsonName,
  JsonStarInfo,
  JsonSubAnonymousAudienceChat,
  JsonSubBoard,
  JsonSubChatFromClient,
  JsonSubChatFromServer,
  JsonSubError,
  JsonSubFlavorText,
  JsonSubGameResult,
  JsonSubOnymousAudienceBoard,
  JsonSubOnymousAudienceChat,
  JsonSubOnymousAudienceScroll,
  JsonSubPhase,
  JsonSubScroll,
  JsonSubStar,
  JsonSubVote,
  JsonUpdate,
  JsonVillage,
  JsonVotingResultDetail,
  JsonVotingResultSummary
}
import licos.json.element.village.character.{
  JsonCharacter,
  JsonResultCharacter,
  JsonRoleCharacter,
  JsonSimpleCharacter,
  JsonStatusCharacter
}
import licos.json.element.village.role.{JsonResultRole, JsonRole, JsonSimpleRole}
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{JsError, JsResult, JsSuccess, JsValue}

import scala.util.{Failure, Success, Try}

trait VillageUnitTestParser {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[VillageUnitTestParser])

  def parseCharacter(jsValue: JsValue): Option[JsonCharacter] = {
    Try(jsValue.validate[JsonCharacter]) match {
      case Success(json: JsResult[JsonCharacter]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseResultCharacter(jsValue: JsValue): Option[JsonResultCharacter] = {
    Try(jsValue.validate[JsonResultCharacter]) match {
      case Success(json: JsResult[JsonResultCharacter]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseRoleCharacter(jsValue: JsValue): Option[JsonRoleCharacter] = {
    Try(jsValue.validate[JsonRoleCharacter]) match {
      case Success(json: JsResult[JsonRoleCharacter]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSimpleCharacter(jsValue: JsValue): Option[JsonSimpleCharacter] = {
    Try(jsValue.validate[JsonSimpleCharacter]) match {
      case Success(json: JsResult[JsonSimpleCharacter]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseStatusCharacter(jsValue: JsValue): Option[JsonStatusCharacter] = {
    Try(jsValue.validate[JsonStatusCharacter]) match {
      case Success(json: JsResult[JsonStatusCharacter]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseResultRole(jsValue: JsValue): Option[JsonResultRole] = {
    Try(jsValue.validate[JsonResultRole]) match {
      case Success(json: JsResult[JsonResultRole]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseRole(jsValue: JsValue): Option[JsonRole] = {
    Try(jsValue.validate[JsonRole]) match {
      case Success(json: JsResult[JsonRole]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSimpleRole(jsValue: JsValue): Option[JsonSimpleRole] = {
    Try(jsValue.validate[JsonSimpleRole]) match {
      case Success(json: JsResult[JsonSimpleRole]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseAvatar(jsValue: JsValue): Option[JsonAvatar] = {
    Try(jsValue.validate[JsonAvatar]) match {
      case Success(json: JsResult[JsonAvatar]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseBase(jsValue: JsValue): Option[JsonBase] = {
    Try(jsValue.validate[JsonBase]) match {
      case Success(json: JsResult[JsonBase]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseBoardResult(jsValue: JsValue): Option[JsonBoardResult] = {
    Try(jsValue.validate[JsonBoardResult]) match {
      case Success(json: JsResult[JsonBoardResult]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseChatSettings(jsValue: JsValue): Option[JsonChatSettings] = {
    Try(jsValue.validate[JsonChatSettings]) match {
      case Success(json: JsResult[JsonChatSettings]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseChatText(jsValue: JsValue): Option[JsonChatText] = {
    Try(jsValue.validate[JsonChatText]) match {
      case Success(json: JsResult[JsonChatText]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseName(jsValue: JsValue): Option[JsonName] = {
    Try(jsValue.validate[JsonName]) match {
      case Success(json: JsResult[JsonName]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseStarInfo(jsValue: JsValue): Option[JsonStarInfo] = {
    Try(jsValue.validate[JsonStarInfo]) match {
      case Success(json: JsResult[JsonStarInfo]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubAnonymousAudienceChat(jsValue: JsValue): Option[JsonSubAnonymousAudienceChat] = {
    Try(jsValue.validate[JsonSubAnonymousAudienceChat]) match {
      case Success(json: JsResult[JsonSubAnonymousAudienceChat]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubOnymousAudienceChat(jsValue: JsValue): Option[JsonSubOnymousAudienceChat] = {
    Try(jsValue.validate[JsonSubOnymousAudienceChat]) match {
      case Success(json: JsResult[JsonSubOnymousAudienceChat]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubOnymousAudienceScroll(jsValue: JsValue): Option[JsonSubOnymousAudienceScroll] = {
    Try(jsValue.validate[JsonSubOnymousAudienceScroll]) match {
      case Success(json: JsResult[JsonSubOnymousAudienceScroll]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubBoard(jsValue: JsValue): Option[JsonSubBoard] = {
    Try(jsValue.validate[JsonSubBoard]) match {
      case Success(json: JsResult[JsonSubBoard]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubOnymousAudienceBoard(jsValue: JsValue): Option[JsonSubOnymousAudienceBoard] = {
    Try(jsValue.validate[JsonSubOnymousAudienceBoard]) match {
      case Success(json: JsResult[JsonSubOnymousAudienceBoard]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubChatFromClient(jsValue: JsValue): Option[JsonSubChatFromClient] = {
    Try(jsValue.validate[JsonSubChatFromClient]) match {
      case Success(json: JsResult[JsonSubChatFromClient]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubChatFromServer(jsValue: JsValue): Option[JsonSubChatFromServer] = {
    Try(jsValue.validate[JsonSubChatFromServer]) match {
      case Success(json: JsResult[JsonSubChatFromServer]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubErrorFromClient(jsValue: JsValue): Option[JsonSubError] = {
    Try(jsValue.validate[JsonSubError]) match {
      case Success(json: JsResult[JsonSubError]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubErrorFromServer(jsValue: JsValue): Option[JsonSubError] = {
    Try(jsValue.validate[JsonSubError]) match {
      case Success(json: JsResult[JsonSubError]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubFlavorText(jsValue: JsValue): Option[JsonSubFlavorText] = {
    Try(jsValue.validate[JsonSubFlavorText]) match {
      case Success(json: JsResult[JsonSubFlavorText]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubGameResult(jsValue: JsValue): Option[JsonSubGameResult] = {
    Try(jsValue.validate[JsonSubGameResult]) match {
      case Success(json: JsResult[JsonSubGameResult]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubPhase(jsValue: JsValue): Option[JsonSubPhase] = {
    Try(jsValue.validate[JsonSubPhase]) match {
      case Success(json: JsResult[JsonSubPhase]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubScroll(jsValue: JsValue): Option[JsonSubScroll] = {
    Try(jsValue.validate[JsonSubScroll]) match {
      case Success(json: JsResult[JsonSubScroll]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubStar(jsValue: JsValue): Option[JsonSubStar] = {
    Try(jsValue.validate[JsonSubStar]) match {
      case Success(json: JsResult[JsonSubStar]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseSubVote(jsValue: JsValue): Option[JsonSubVote] = {
    Try(jsValue.validate[JsonSubVote]) match {
      case Success(json: JsResult[JsonSubVote]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseUpdate(jsValue: JsValue): Option[JsonUpdate] = {
    Try(jsValue.validate[JsonUpdate]) match {
      case Success(json: JsResult[JsonUpdate]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseVillage(jsValue: JsValue): Option[JsonVillage] = {
    Try(jsValue.validate[JsonVillage]) match {
      case Success(json: JsResult[JsonVillage]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseVotingResultDetail(jsValue: JsValue): Option[JsonVotingResultDetail] = {
    Try(jsValue.validate[JsonVotingResultDetail]) match {
      case Success(json: JsResult[JsonVotingResultDetail]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseVotingResultSummary(jsValue: JsValue): Option[JsonVotingResultSummary] = {
    Try(jsValue.validate[JsonVotingResultSummary]) match {
      case Success(json: JsResult[JsonVotingResultSummary]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

}
