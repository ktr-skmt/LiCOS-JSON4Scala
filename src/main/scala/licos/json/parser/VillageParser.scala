package licos.json.parser

import licos.json.element.village.invite.{JsonNextGameInvitation, JsonNextGameInvitationIsClosed}
import licos.json.element.village._
import licos.json.element.village.receipt.{
  JsonReceivedChatMessage,
  JsonReceivedFlavorTextMessage,
  JsonReceivedSystemMessage
}
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{JsError, JsResult, JsSuccess, JsValue}

import scala.util.{Failure, Success, Try}

/** This JSON parser lets classes extend parse methods for village.
  *
  * @author Kotaro Sakamoto
  */
trait VillageParser extends LiCOSParser {

  private val logger: Logger = LoggerFactory.getLogger(classOf[LiCOSParser])

  /** Tries to parse play.api.libs.json.JsValue as Phase JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Phase JSON option.
    */
  def parsePhase(jsValue: JsValue): Option[JsonPhase] = {
    Try(jsValue.validate[JsonPhase]) match {
      case Success(json: JsResult[JsonPhase]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Flavor-text JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Flavor-text JSON option.
    */
  def parseFlavorText(jsValue: JsValue): Option[JsonFlavorText] = {
    Try(jsValue.validate[JsonFlavorText]) match {
      case Success(json: JsResult[JsonFlavorText]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Game-result JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Game-result JSON option.
    */
  def parseGameResult(jsValue: JsValue): Option[JsonGameResult] = {
    Try(jsValue.validate[JsonGameResult]) match {
      case Success(json: JsResult[JsonGameResult]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Error JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Error JSON option.
    */
  def parseError(jsValue: JsValue): Option[JsonError] = {
    Try(jsValue.validate[JsonError]) match {
      case Success(json: JsResult[JsonError]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Board JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Board JSON option.
    */
  protected def parseBoard(jsValue: JsValue): Option[JsonBoard] = {
    Try(jsValue.validate[JsonBoard]) match {
      case Success(json: JsResult[JsonBoard]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Onymous Audience Board JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Board JSON option.
    */
  protected def parseOnymousAudienceBoard(jsValue: JsValue): Option[JsonOnymousAudienceBoard] = {
    Try(jsValue.validate[JsonOnymousAudienceBoard]) match {
      case Success(json: JsResult[JsonOnymousAudienceBoard]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Star JSON.
    *
    * @param jsValue a play.api.libs.jsob.JsValue to parse
    * @return a Star JSON option
    */
  protected def parseStar(jsValue: JsValue): Option[JsonStar] = {
    Try(jsValue.validate[JsonStar]) match {
      case Success(json: JsResult[JsonStar]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Chat-from-client JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Chat-from-client JSON option.
    */
  protected def parseChatFromClient(jsValue: JsValue): Option[JsonChatFromClient] = {
    Try(jsValue.validate[JsonChatFromClient]) match {
      case Success(json: JsResult[JsonChatFromClient]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Chat-from-server JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Chat-from-server JSON option.
    */
  protected def parseChatFromServer(jsValue: JsValue): Option[JsonChatFromServer] = {
    Try(jsValue.validate[JsonChatFromServer]) match {
      case Success(json: JsResult[JsonChatFromServer]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Onymous-audience-chat JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return an Onymous-audience-chat JSON option.
    */
  protected def parseOnymousAudienceChat(jsValue: JsValue): Option[JsonOnymousAudienceChat] = {
    Try(jsValue.validate[JsonOnymousAudienceChat]) match {
      case Success(json: JsResult[JsonOnymousAudienceChat]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Anonymous-audience-chat JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return an Anonymous-audience-chat JSON option.
    */
  protected def parseAnonymousAudienceChat(jsValue: JsValue): Option[JsonAnonymousAudienceChat] = {
    Try(jsValue.validate[JsonAnonymousAudienceChat]) match {
      case Success(json: JsResult[JsonAnonymousAudienceChat]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Vote JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Vote JSON option.
    */
  protected def parseVote(jsValue: JsValue): Option[JsonVote] = {
    Try(jsValue.validate[JsonVote]) match {
      case Success(json: JsResult[JsonVote]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Scroll JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Scroll JSON option.
    */
  protected def parseScroll(jsValue: JsValue): Option[JsonScroll] = {
    Try(jsValue.validate[JsonScroll]) match {
      case Success(json: JsResult[JsonScroll]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Onymous-audience-scroll JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Scroll JSON option.
    */
  protected def parseOnymousAudienceScroll(jsValue: JsValue): Option[JsonOnymousAudienceScroll] = {
    Try(jsValue.validate[JsonOnymousAudienceScroll]) match {
      case Success(json: JsResult[JsonOnymousAudienceScroll]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Received-chat-message JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Received-chat-message JSON option.
    */
  def parseReceivedChatMessage(jsValue: JsValue): Option[JsonReceivedChatMessage] = {
    Try(jsValue.validate[JsonReceivedChatMessage]) match {
      case Success(json: JsResult[JsonReceivedChatMessage]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Received-system-message JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Received-system-message JSON option.
    */
  def parseReceivedSystemMessage(jsValue: JsValue): Option[JsonReceivedSystemMessage] = {
    Try(jsValue.validate[JsonReceivedSystemMessage]) match {
      case Success(json: JsResult[JsonReceivedSystemMessage]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Received-flavor-text JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Received-flavor-text JSON option.
    */
  def parseReceivedFlavorTextMessage(jsValue: JsValue): Option[JsonReceivedFlavorTextMessage] = {
    Try(jsValue.validate[JsonReceivedFlavorTextMessage]) match {
      case Success(json: JsResult[JsonReceivedFlavorTextMessage]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Next-game-invitation-is-closed JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Next-game-invitation-is-closed JSON option.
    */
  def parseNextGameInvitationIsClosed(jsValue: JsValue): Option[JsonNextGameInvitationIsClosed] = {
    Try(jsValue.validate[JsonNextGameInvitationIsClosed]) match {
      case Success(json: JsResult[JsonNextGameInvitationIsClosed]) =>
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

  /** Tries to parse play.api.libs.json.JsValue as Next-game-invitation JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Next-game-invitation JSON option.
    */
  def parseNextGameInvitation(jsValue: JsValue): Option[JsonNextGameInvitation] = {
    Try(jsValue.validate[JsonNextGameInvitation]) match {
      case Success(json: JsResult[JsonNextGameInvitation]) =>
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
