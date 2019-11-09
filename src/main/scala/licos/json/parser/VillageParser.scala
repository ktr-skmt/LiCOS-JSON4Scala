package licos.json.parser

import licos.json.element.village.invite.{JsonNextGameInvitation, JsonNextGameInvitationIsClosed}
import licos.json.element.village._
import licos.json.element.village.receipt.{
  JsonReceivedChatMessage,
  JsonReceivedFlavorTextMessage,
  JsonReceivedSystemMessage
}
import licos.json.engine.analysis.village.{client2server, server2client}
import licos.json.engine.analysis.village.client2server.{
  BoardAnalysisEngine,
  OnymousAudienceBoardAnalysisEngine,
  OnymousAudienceScrollAnalysisEngine,
  ReceivedChatMessageAnalysisEngine,
  ReceivedFlavorTextMessageAnalysisEngine,
  ReceivedSystemMessageAnalysisEngine,
  ScrollAnalysisEngine,
  StarAnalysisEngine,
  VoteAnalysisEngine
}
import licos.json.engine.analysis.village.server2client.{
  FlavorTextAnalysisEngine,
  GameResultAnalysisEngine,
  NextGameInvitationAnalysisEngine,
  NextGameInvitationIsClosedAnalysisEngine,
  PhaseAnalysisEngine
}
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{JsError, JsResult, JsSuccess, JsValue, Json}

import scala.util.{Failure, Success, Try}

/** This JSON parser lets classes extend parse methods for village.
  *
  * @author Kotaro Sakamoto
  */
@SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
trait VillageParser extends LiCOSParser {

  private val log: Logger = LoggerFactory.getLogger(classOf[LiCOSParser])

  /** Tries to parse play.api.libs.json.JsValue as Phase JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Phase JSON option.
    */
  def parsePhase(jsValue: JsValue): Either[JsValue, JsonPhase] = {
    Try(jsValue.validate[JsonPhase]) match {
      case Success(json: JsResult[JsonPhase]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, PhaseAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, PhaseAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Flavor-text JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Flavor-text JSON option.
    */
  def parseFlavorText(jsValue: JsValue): Either[JsValue, JsonFlavorText] = {
    Try(jsValue.validate[JsonFlavorText]) match {
      case Success(json: JsResult[JsonFlavorText]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, FlavorTextAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, FlavorTextAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Game-result JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Game-result JSON option.
    */
  def parseGameResult(jsValue: JsValue): Either[JsValue, JsonGameResult] = {
    Try(jsValue.validate[JsonGameResult]) match {
      case Success(json: JsResult[JsonGameResult]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, GameResultAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, GameResultAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Error JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Error JSON option.
    */
  def parseError(jsValue: JsValue): Either[JsValue, JsonError] = {
    Try(jsValue.validate[JsonError]) match {
      case Success(json: JsResult[JsonError]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, isFromServer = true))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, isFromServer = true))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Board JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Board JSON option.
    */
  protected def parseBoard(jsValue: JsValue): Either[JsValue, JsonBoard] = {
    Try(jsValue.validate[JsonBoard]) match {
      case Success(json: JsResult[JsonBoard]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, BoardAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, BoardAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Onymous Audience Board JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Board JSON option.
    */
  protected def parseOnymousAudienceBoard(jsValue: JsValue): Either[JsValue, JsonOnymousAudienceBoard] = {
    Try(jsValue.validate[JsonOnymousAudienceBoard]) match {
      case Success(json: JsResult[JsonOnymousAudienceBoard]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, OnymousAudienceBoardAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, OnymousAudienceBoardAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Star JSON.
    *
    * @param jsValue a play.api.libs.jsob.JsValue to parse
    * @return a Star JSON option
    */
  protected def parseStar(jsValue: JsValue): Either[JsValue, JsonStar] = {
    Try(jsValue.validate[JsonStar]) match {
      case Success(json: JsResult[JsonStar]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, StarAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, StarAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Chat-from-client JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Chat-from-client JSON option.
    */
  protected def parseChatFromClient(jsValue: JsValue): Either[JsValue, JsonChatFromClient] = {
    Try(jsValue.validate[JsonChatFromClient]) match {
      case Success(json: JsResult[JsonChatFromClient]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, client2server.ChatAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, client2server.ChatAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Chat-from-server JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Chat-from-server JSON option.
    */
  protected def parseChatFromServer(jsValue: JsValue): Either[JsValue, JsonChatFromServer] = {
    Try(jsValue.validate[JsonChatFromServer]) match {
      case Success(json: JsResult[JsonChatFromServer]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, server2client.ChatAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, server2client.ChatAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Onymous-audience-chat JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return an Onymous-audience-chat JSON option.
    */
  protected def parseOnymousAudienceChat(jsValue: JsValue): Either[JsValue, JsonOnymousAudienceChat] = {
    Try(jsValue.validate[JsonOnymousAudienceChat]) match {
      case Success(json: JsResult[JsonOnymousAudienceChat]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, isFromServer = true))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, isFromServer = true))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Anonymous-audience-chat JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return an Anonymous-audience-chat JSON option.
    */
  protected def parseAnonymousAudienceChat(jsValue: JsValue): Either[JsValue, JsonAnonymousAudienceChat] = {
    Try(jsValue.validate[JsonAnonymousAudienceChat]) match {
      case Success(json: JsResult[JsonAnonymousAudienceChat]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, isFromServer = true))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, isFromServer = true))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Vote JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Vote JSON option.
    */
  protected def parseVote(jsValue: JsValue): Either[JsValue, JsonVote] = {
    Try(jsValue.validate[JsonVote]) match {
      case Success(json: JsResult[JsonVote]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, VoteAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, VoteAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Scroll JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Scroll JSON option.
    */
  protected def parseScroll(jsValue: JsValue): Either[JsValue, JsonScroll] = {
    Try(jsValue.validate[JsonScroll]) match {
      case Success(json: JsResult[JsonScroll]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ScrollAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ScrollAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Onymous-audience-scroll JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Scroll JSON option.
    */
  protected def parseOnymousAudienceScroll(jsValue: JsValue): Either[JsValue, JsonOnymousAudienceScroll] = {
    Try(jsValue.validate[JsonOnymousAudienceScroll]) match {
      case Success(json: JsResult[JsonOnymousAudienceScroll]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, OnymousAudienceScrollAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, OnymousAudienceScrollAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Received-chat-message JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Received-chat-message JSON option.
    */
  def parseReceivedChatMessage(jsValue: JsValue): Either[JsValue, JsonReceivedChatMessage] = {
    Try(jsValue.validate[JsonReceivedChatMessage]) match {
      case Success(json: JsResult[JsonReceivedChatMessage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ReceivedChatMessageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ReceivedChatMessageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Received-system-message JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Received-system-message JSON option.
    */
  def parseReceivedSystemMessage(jsValue: JsValue): Either[JsValue, JsonReceivedSystemMessage] = {
    Try(jsValue.validate[JsonReceivedSystemMessage]) match {
      case Success(json: JsResult[JsonReceivedSystemMessage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ReceivedSystemMessageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ReceivedSystemMessageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Received-flavor-text JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Received-flavor-text JSON option.
    */
  def parseReceivedFlavorTextMessage(jsValue: JsValue): Either[JsValue, JsonReceivedFlavorTextMessage] = {
    Try(jsValue.validate[JsonReceivedFlavorTextMessage]) match {
      case Success(json: JsResult[JsonReceivedFlavorTextMessage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ReceivedFlavorTextMessageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ReceivedFlavorTextMessageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Next-game-invitation-is-closed JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Next-game-invitation-is-closed JSON option.
    */
  def parseNextGameInvitationIsClosed(jsValue: JsValue): Either[JsValue, JsonNextGameInvitationIsClosed] = {
    Try(jsValue.validate[JsonNextGameInvitationIsClosed]) match {
      case Success(json: JsResult[JsonNextGameInvitationIsClosed]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, NextGameInvitationIsClosedAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, NextGameInvitationIsClosedAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Next-game-invitation JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Next-game-invitation JSON option.
    */
  def parseNextGameInvitation(jsValue: JsValue): Either[JsValue, JsonNextGameInvitation] = {
    Try(jsValue.validate[JsonNextGameInvitation]) match {
      case Success(json: JsResult[JsonNextGameInvitation]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, NextGameInvitationAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, NextGameInvitationAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Story JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Story JSON option.
    */
  def parseStory(jsValue: JsValue): Either[JsValue, JsonStory] = {
    Try(jsValue.validate[JsonStory]) match {
      case Success(json: JsResult[JsonStory]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, isFromServer = true))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, isFromServer = true))
    }
  }
}
