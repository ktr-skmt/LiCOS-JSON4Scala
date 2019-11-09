package licos.json.parser

import licos.json.element.lobby.{
  JsonAdvancedSearch,
  JsonAvatarInfo,
  JsonChangeLang,
  JsonChangeUserEmail,
  JsonChangeUserName,
  JsonChangeUserPassword,
  JsonEnterLobby,
  JsonGetAvatarInfo,
  JsonGetSettings,
  JsonIdSearch,
  JsonKickOutPlayer,
  JsonLobby,
  JsonPing,
  JsonPlay,
  JsonPlayed,
  JsonPlayedWithToken,
  JsonPong,
  JsonSearchResult,
  JsonSelectVillage,
  JsonSettings,
  JsonWaitingPage
}
import licos.json.engine.analysis.lobby.client2server.{
  AdvancedSearchAnalysisEngine,
  ChangeLangAnalysisEngine,
  ChangeUserEmailAnalysisEngine,
  ChangeUserNameAnalysisEngine,
  ChangeUserPasswordAnalysisEngine,
  EnterLobbyAnalysisEngine,
  GetAvatarInfoAnalysisEngine,
  GetSettingsAnalysisEngine,
  IdSearchAnalysisEngine,
  KickOutPlayerAnalysisEngine,
  PlayAnalysisEngine,
  PongAnalysisEngine,
  SelectVillageAnalysisEngine
}
import licos.json.engine.analysis.lobby.server2client.{
  AvatarInfoAnalysisEngine,
  LobbyAnalysisEngine,
  PingAnalysisEngine,
  PlayedAnalysisEngine,
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

  private val log: Logger = LoggerFactory.getLogger(classOf[LiCOSParser])

  /** Tries to parse play.api.libs.json.JsValue as Ping JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Ping JSON option.
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
    * @return a Pong JSON option.
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
    * @return a Waiting-page JSON option.
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
    * @return a Lobby JSON option.
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
    * @return a Enter-lobby JSON option.
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
    * @return a Get-avatar-info JSON option.
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
    * @return an Avatar-info JSON option.
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
    * @return a Select-village JSON option.
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
    * @return a Kick-out-player JSON option.
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
    * @return a Advanced-search JSON option.
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
    * @return a Id-search JSON option.
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
    * @return a Play JSON option.
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
    * @return a Played JSON option.
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
    * @return a Played-with-token JSON option.
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
    * @return a Search-result JSON option.
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

  /** Tries to parse play.api.libs.json.JsValue as Change-lang JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Change-lang JSON option.
    */
  protected def parseChangeLang(jsValue: JsValue): Either[JsValue, JsonChangeLang] = {
    Try(jsValue.validate[JsonChangeLang]) match {
      case Success(json: JsResult[JsonChangeLang]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ChangeLangAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ChangeLangAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Change-user-email JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Change-user-email JSON option.
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
    * @return a Change-user-name JSON option.
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
    * @return a Change-user-password JSON option.
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
    * @return a Get-settings JSON option.
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

  /** Tries to parse play.api.libs.json.JsValue as Settings JSON
    *
    * @param jsValue a play.api.libs.json.JsValue to parse
    * @return a Settings JSON option.
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
}
