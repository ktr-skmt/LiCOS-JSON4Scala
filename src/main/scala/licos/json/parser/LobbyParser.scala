package licos.json.parser

import licos.json.element.lobby.client2server.{
  JsonAdvancedSearch,
  JsonAuthorizationRequestAccepted,
  JsonChangeLanguage,
  JsonChangeUserEmail,
  JsonChangeUserName,
  JsonChangeUserPassword,
  JsonCreateHumanPlayer,
  JsonCreateOnymousAudience,
  JsonCreateRobotPlayer,
  JsonDeleteAvatar,
  JsonEnterAvatarSelectionPage,
  JsonEnterLobby,
  JsonGetSettings,
  JsonIdSearch,
  JsonKickOutPlayer,
  JsonPlay,
  JsonPong,
  JsonRenewAvatarToken,
  JsonRunRobotPlayerInTheBackground,
  JsonSelectVillage,
  JsonStopRobotPlayer,
  JsonUpdateAvatar
}
import licos.json.element.lobby.server2client.{
  JsonAuthorizationRequest,
  JsonAuthorizationRequestAcceptedResponse,
  JsonAvatarInfo,
  JsonGetAvatarInfo,
  JsonHumanPlayerSelectionPage,
  JsonLobby,
  JsonOnymousAudienceSelectionPage,
  JsonPing,
  JsonPlayed,
  JsonRobotPlayerSelectionPage,
  JsonSearchResult,
  JsonSettings,
  JsonWaitingPage
}
import licos.json.element.lobby.server2server.JsonPlayedWithToken
import licos.json.engine.analysis.lobby.client2server.{
  AdvancedSearchAnalysisEngine,
  AuthorizationRequestAcceptedAnalysisEngine,
  ChangeLanguageAnalysisEngine,
  ChangeUserEmailAnalysisEngine,
  ChangeUserNameAnalysisEngine,
  ChangeUserPasswordAnalysisEngine,
  CreateHumanPlayerAnalysisEngine,
  CreateOnymousAudienceAnalysisEngine,
  CreateRobotPlayerAnalysisEngine,
  DeleteAvatarAnalysisEngine,
  EnterAvatarSelectionPageAnalysisEngine,
  EnterLobbyAnalysisEngine,
  GetAvatarInfoAnalysisEngine,
  GetSettingsAnalysisEngine,
  IdSearchAnalysisEngine,
  KickOutPlayerAnalysisEngine,
  PlayAnalysisEngine,
  PongAnalysisEngine,
  RenewAvatarTokenAnalysisEngine,
  RunRobotPlayerInTheBackgroundAnalysisEngine,
  SelectVillageAnalysisEngine,
  StopRobotPlayerAnalysisEngine,
  UpdateAvatarAnalysisEngine
}
import licos.json.engine.analysis.lobby.server2client.{
  AuthorizationRequestAcceptedResponseAnalysisEngine,
  AuthorizationRequestAnalysisEngine,
  AvatarInfoAnalysisEngine,
  HumanPlayerSelectionPageAnalysisEngine,
  LobbyAnalysisEngine,
  OnymousAudienceSelectionPageAnalysisEngine,
  PingAnalysisEngine,
  PlayedAnalysisEngine,
  RobotPlayerSelectionPageAnalysisEngine,
  SearchResultAnalysisEngine,
  SettingsAnalysisEngine,
  WaitingPageAnalysisEngine
}
import licos.json.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{JsError, JsResult, JsSuccess, JsValue, Json}

import scala.util.{Failure, Success, Try}

/** This JSON parser lets classes extend parse methods for lobby.
  *
  * @author Kotaro Sakamoto
  */
@SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
trait LobbyParser extends LiCOSParser {

  private val log: Logger = LoggerFactory.getLogger(classOf[LobbyParser])

  /** Tries to parse play.api.libs.json.JsValue as Ping JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Ping JSON.
    */
  protected def parsePing(jsValue: JsValue): Either[JsValue, JsonPing] = {
    Try(jsValue.validate[JsonPing]) match {
      case Success(json: JsResult[JsonPing]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, PingAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, PingAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Pong JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Pong JSON.
    */
  protected def parsePong(jsValue: JsValue): Either[JsValue, JsonPong] = {
    Try(jsValue.validate[JsonPong]) match {
      case Success(json: JsResult[JsonPong]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, PongAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, PongAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Waiting-page JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Waiting-page JSON.
    */
  def parseWaitingPage(jsValue: JsValue): Either[JsValue, JsonWaitingPage] = {
    Try(jsValue.validate[JsonWaitingPage]) match {
      case Success(json: JsResult[JsonWaitingPage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, WaitingPageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, WaitingPageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Lobby JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Lobby JSON.
    */
  protected def parseLobby(jsValue: JsValue): Either[JsValue, JsonLobby] = {
    Try(jsValue.validate[JsonLobby]) match {
      case Success(json: JsResult[JsonLobby]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, LobbyAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, LobbyAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Enter-lobby JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Enter-lobby JSON.
    */
  protected def parseEnterLobby(jsValue: JsValue): Either[JsValue, JsonEnterLobby] = {
    Try(jsValue.validate[JsonEnterLobby]) match {
      case Success(json: JsResult[JsonEnterLobby]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, EnterLobbyAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, EnterLobbyAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Get-avatar-info JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Get-avatar-info JSON.
    */
  protected def parseGetAvatarInfo(jsValue: JsValue): Either[JsValue, JsonGetAvatarInfo] = {
    Try(jsValue.validate[JsonGetAvatarInfo]) match {
      case Success(json: JsResult[JsonGetAvatarInfo]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, GetAvatarInfoAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, GetAvatarInfoAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Avatar-info JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse
    * @return an Avatar-info JSON.
    */
  protected def parseAvatarInfo(jsValue: JsValue): Either[JsValue, JsonAvatarInfo] = {
    Try(jsValue.validate[JsonAvatarInfo]) match {
      case Success(json: JsResult[JsonAvatarInfo]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, AvatarInfoAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, AvatarInfoAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Select-village JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Select-village JSON.
    */
  protected def parseSelectVillage(jsValue: JsValue): Either[JsValue, JsonSelectVillage] = {
    Try(jsValue.validate[JsonSelectVillage]) match {
      case Success(json: JsResult[JsonSelectVillage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, SelectVillageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, SelectVillageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Kick-out-player JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Kick-out-player JSON.
    */
  protected def parseKickOutPlayer(jsValue: JsValue): Either[JsValue, JsonKickOutPlayer] = {
    Try(jsValue.validate[JsonKickOutPlayer]) match {
      case Success(json: JsResult[JsonKickOutPlayer]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, KickOutPlayerAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, KickOutPlayerAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Advanced-search JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Advanced-search JSON.
    */
  protected def parseAdvancedSearch(jsValue: JsValue): Either[JsValue, JsonAdvancedSearch] = {
    Try(jsValue.validate[JsonAdvancedSearch]) match {
      case Success(json: JsResult[JsonAdvancedSearch]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, AdvancedSearchAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, AdvancedSearchAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Id-search JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Id-search JSON.
    */
  protected def parseIdSearch(jsValue: JsValue): Either[JsValue, JsonIdSearch] = {
    Try(jsValue.validate[JsonIdSearch]) match {
      case Success(json: JsResult[JsonIdSearch]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, IdSearchAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, IdSearchAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Play JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Play JSON.
    */
  protected def parsePlay(jsValue: JsValue): Either[JsValue, JsonPlay] = {
    Try(jsValue.validate[JsonPlay]) match {
      case Success(json: JsResult[JsonPlay]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, PlayAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, PlayAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Played JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Played JSON.
    */
  protected def parsePlayed(jsValue: JsValue): Either[JsValue, JsonPlayed] = {
    Try(jsValue.validate[JsonPlayed]) match {
      case Success(json: JsResult[JsonPlayed]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, PlayedAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, PlayedAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Played-with-token JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Played-with-token JSON.
    */
  protected def parsePlayedWithToken(jsValue: JsValue): Either[JsValue, JsonPlayedWithToken] = {
    Try(jsValue.validate[JsonPlayedWithToken]) match {
      case Success(json: JsResult[JsonPlayedWithToken]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, PlayedWithTokenAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, PlayedWithTokenAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Search-result JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Search-result JSON.
    */
  protected def parseSearchResult(jsValue: JsValue): Either[JsValue, JsonSearchResult] = {
    Try(jsValue.validate[JsonSearchResult]) match {
      case Success(json: JsResult[JsonSearchResult]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, SearchResultAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, SearchResultAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Change-language JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Change-language JSON.
    */
  protected def parseChangeLanguage(jsValue: JsValue): Either[JsValue, JsonChangeLanguage] = {
    Try(jsValue.validate[JsonChangeLanguage]) match {
      case Success(json: JsResult[JsonChangeLanguage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ChangeLanguageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ChangeLanguageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Change-user-email JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Change-user-email JSON.
    */
  protected def parseChangeUserEmail(jsValue: JsValue): Either[JsValue, JsonChangeUserEmail] = {
    Try(jsValue.validate[JsonChangeUserEmail]) match {
      case Success(json: JsResult[JsonChangeUserEmail]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ChangeUserEmailAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ChangeUserEmailAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Change-user-name JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Change-user-name JSON.
    */
  protected def parseChangeUserName(jsValue: JsValue): Either[JsValue, JsonChangeUserName] = {
    Try(jsValue.validate[JsonChangeUserName]) match {
      case Success(json: JsResult[JsonChangeUserName]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ChangeUserNameAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ChangeUserNameAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Change-user-password JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Change-user-password JSON.
    */
  protected def parseChangeUserPassword(jsValue: JsValue): Either[JsValue, JsonChangeUserPassword] = {
    Try(jsValue.validate[JsonChangeUserPassword]) match {
      case Success(json: JsResult[JsonChangeUserPassword]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ChangeUserPasswordAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ChangeUserPasswordAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Get-settings JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Get-settings JSON.
    */
  protected def parseGetSettings(jsValue: JsValue): Either[JsValue, JsonGetSettings] = {
    Try(jsValue.validate[JsonGetSettings]) match {
      case Success(json: JsResult[JsonGetSettings]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, GetSettingsAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, GetSettingsAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Settings JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Settings JSON.
    */
  protected def parseSettings(jsValue: JsValue): Either[JsValue, JsonSettings] = {
    Try(jsValue.validate[JsonSettings]) match {
      case Success(json: JsResult[JsonSettings]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, SettingsAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, SettingsAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Authorization-request-accepted JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Authorization-request-accepted JSON.
    */
  protected def parseAuthorizationRequestAccepted(
      jsValue: JsValue
  ): Either[JsValue, JsonAuthorizationRequestAccepted] = {
    Try(jsValue.validate[JsonAuthorizationRequestAccepted]) match {
      case Success(json: JsResult[JsonAuthorizationRequestAccepted]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, AuthorizationRequestAcceptedAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, AuthorizationRequestAcceptedAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Authorization-request-accepted-response JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Authorization-request-accepted-response JSON.
    */
  protected def parseAuthorizationRequestAcceptedResponse(
      jsValue: JsValue
  ): Either[JsValue, JsonAuthorizationRequestAcceptedResponse] = {
    Try(jsValue.validate[JsonAuthorizationRequestAcceptedResponse]) match {
      case Success(json: JsResult[JsonAuthorizationRequestAcceptedResponse]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, AuthorizationRequestAcceptedResponseAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, AuthorizationRequestAcceptedResponseAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Authorization-request JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Authorization-request JSON.
    */
  protected def parseAuthorizationRequest(jsValue: JsValue): Either[JsValue, JsonAuthorizationRequest] = {
    Try(jsValue.validate[JsonAuthorizationRequest]) match {
      case Success(json: JsResult[JsonAuthorizationRequest]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, AuthorizationRequestAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, AuthorizationRequestAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Renew-avatar-token JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Renew-avatar-token JSON.
    */
  protected def parseRenewAvatarToken(jsValue: JsValue): Either[JsValue, JsonRenewAvatarToken] = {
    Try(jsValue.validate[JsonRenewAvatarToken]) match {
      case Success(json: JsResult[JsonRenewAvatarToken]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, RenewAvatarTokenAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, RenewAvatarTokenAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Create-human-player JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Create-human-player JSON.
    */
  protected def parseCreateHumanPlayer(jsValue: JsValue): Either[JsValue, JsonCreateHumanPlayer] = {
    Try(jsValue.validate[JsonCreateHumanPlayer]) match {
      case Success(json: JsResult[JsonCreateHumanPlayer]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, CreateHumanPlayerAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, CreateHumanPlayerAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Create-onymous-audience JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Create-onymous-audience JSON.
    */
  protected def parseCreateOnymousAudience(jsValue: JsValue): Either[JsValue, JsonCreateOnymousAudience] = {
    Try(jsValue.validate[JsonCreateOnymousAudience]) match {
      case Success(json: JsResult[JsonCreateOnymousAudience]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, CreateOnymousAudienceAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, CreateOnymousAudienceAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Create-robot-player JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Create-robot-player JSON.
    */
  protected def parseCreateRobotPlayer(jsValue: JsValue): Either[JsValue, JsonCreateRobotPlayer] = {
    Try(jsValue.validate[JsonCreateRobotPlayer]) match {
      case Success(json: JsResult[JsonCreateRobotPlayer]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, CreateRobotPlayerAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, CreateRobotPlayerAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Delete-avatar JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Delete-avatar JSON.
    */
  protected def parseDeleteAvatar(jsValue: JsValue): Either[JsValue, JsonDeleteAvatar] = {
    Try(jsValue.validate[JsonDeleteAvatar]) match {
      case Success(json: JsResult[JsonDeleteAvatar]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, DeleteAvatarAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, DeleteAvatarAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Run-robot-player-in-the-background JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Run-robot-player-in-the-background JSON.
    */
  protected def parseRunRobotPlayerInTheBackground(
      jsValue: JsValue
  ): Either[JsValue, JsonRunRobotPlayerInTheBackground] = {
    Try(jsValue.validate[JsonRunRobotPlayerInTheBackground]) match {
      case Success(json: JsResult[JsonRunRobotPlayerInTheBackground]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, RunRobotPlayerInTheBackgroundAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, RunRobotPlayerInTheBackgroundAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Stop-robot-player JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Stop-robot-player JSON.
    */
  protected def parseStopRobotPlayer(jsValue: JsValue): Either[JsValue, JsonStopRobotPlayer] = {
    Try(jsValue.validate[JsonStopRobotPlayer]) match {
      case Success(json: JsResult[JsonStopRobotPlayer]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, StopRobotPlayerAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, StopRobotPlayerAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Human-player-selection-page JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Human-player-selection-page JSON.
    */
  protected def parseHumanPlayerSelectionPage(jsValue: JsValue): Either[JsValue, JsonHumanPlayerSelectionPage] = {
    Try(jsValue.validate[JsonHumanPlayerSelectionPage]) match {
      case Success(json: JsResult[JsonHumanPlayerSelectionPage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, HumanPlayerSelectionPageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, HumanPlayerSelectionPageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Onymous-audience-selection-page JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Onymous-audience-selection-page JSON.
    */
  protected def parseOnymousAudienceSelectionPage(
      jsValue: JsValue
  ): Either[JsValue, JsonOnymousAudienceSelectionPage] = {
    Try(jsValue.validate[JsonOnymousAudienceSelectionPage]) match {
      case Success(json: JsResult[JsonOnymousAudienceSelectionPage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, OnymousAudienceSelectionPageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, OnymousAudienceSelectionPageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Robot-player-selection-page JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Robot-player-selection-page JSON.
    */
  protected def parseRobotPlayerSelectionPage(jsValue: JsValue): Either[JsValue, JsonRobotPlayerSelectionPage] = {
    Try(jsValue.validate[JsonRobotPlayerSelectionPage]) match {
      case Success(json: JsResult[JsonRobotPlayerSelectionPage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, RobotPlayerSelectionPageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, RobotPlayerSelectionPageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Enter-avatar-selection-page JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Enter-avatar-selection-page JSON.
    */
  protected def parseEnterAvatarSelectionPage(jsValue: JsValue): Either[JsValue, JsonEnterAvatarSelectionPage] = {
    Try(jsValue.validate[JsonEnterAvatarSelectionPage]) match {
      case Success(json: JsResult[JsonEnterAvatarSelectionPage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, EnterAvatarSelectionPageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, EnterAvatarSelectionPageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Update-avatar JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Update-avatar JSON.
    */
  protected def parseUpdateAvatar(jsValue: JsValue): Either[JsValue, JsonUpdateAvatar] = {
    Try(jsValue.validate[JsonUpdateAvatar]) match {
      case Success(json: JsResult[JsonUpdateAvatar]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, UpdateAvatarAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, UpdateAvatarAnalysisEngine.isFromServer))
    }
  }
}
