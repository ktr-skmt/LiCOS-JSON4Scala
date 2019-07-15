package licos.json.parser

import licos.json.lobby.{JsonAdvancedSearch, JsonChangeLang, JsonChangeUserEmail, JsonChangeUserName, JsonChangeUserPassword, JsonEnterLobby, JsonGetAvatarInfo, JsonGetSettings, JsonIdSearch, JsonKickOutPlayer, JsonLobby, JsonPing, JsonPlay, JsonPlayedWithToken, JsonPong, JsonSearchResult, JsonSelectVillage, JsonWaitingPage}
import play.api.libs.json.{JsResult, JsValue}

import scala.util.{Failure, Success, Try}

/** This JSON parser lets classes extend parse methods for lobby.
  *
  * @author Kotaro Sakamoto
  */
trait LobbyParser extends LiCOSParser {

  /** Tries to parse play.api.libs.json.JsValue as Ping JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Ping JSON option.
    */
  protected def parsePing(jsValue: JsValue): Option[JsonPing] = {
    Try(jsValue.validate[JsonPing]) match {
      case Success(json: JsResult[JsonPing]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Pong JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Pong JSON option.
    */
  protected def parsePong(jsValue: JsValue): Option[JsonPong] = {
    Try(jsValue.validate[JsonPong]) match {
      case Success(json: JsResult[JsonPong]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Waiting-page JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Waiting-page JSON option.
    */
  def parseWaitingPage(jsValue: JsValue): Option[JsonWaitingPage] = {
    Try(jsValue.validate[JsonWaitingPage]) match {
      case Success(json: JsResult[JsonWaitingPage]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Lobby JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Lobby JSON option.
    */
  protected def parseLobby(jsValue: JsValue): Option[JsonLobby] = {
    Try(jsValue.validate[JsonLobby]) match {
      case Success(json: JsResult[JsonLobby]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Enter-lobby JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Enter-lobby JSON option.
    */
  protected def parseEnterLobby(jsValue: JsValue): Option[JsonEnterLobby] = {
    Try(jsValue.validate[JsonEnterLobby]) match {
      case Success(json: JsResult[JsonEnterLobby]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Get-avatar-info JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Get-avatar-info JSON option.
    */
  protected def parseGetAvatarInfo(jsValue: JsValue): Option[JsonGetAvatarInfo] = {
    Try(jsValue.validate[JsonGetAvatarInfo]) match {
      case Success(json: JsResult[JsonGetAvatarInfo]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Select-village JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Select-village JSON option.
    */
  protected def parseSelectVillage(jsValue: JsValue): Option[JsonSelectVillage] = {
    Try(jsValue.validate[JsonSelectVillage]) match {
      case Success(json: JsResult[JsonSelectVillage]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Kick-out-player JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Kick-out-player JSON option.
    */
  protected def parseKickOutPlayer(jsValue: JsValue): Option[JsonKickOutPlayer] = {
    Try(jsValue.validate[JsonKickOutPlayer]) match {
      case Success(json: JsResult[JsonKickOutPlayer]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Advanced-search JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Advanced-search JSON option.
    */
  protected def parseAdvancedSearch(jsValue: JsValue): Option[JsonAdvancedSearch] = {
    Try(jsValue.validate[JsonAdvancedSearch]) match {
      case Success(json: JsResult[JsonAdvancedSearch]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Id-search JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Id-search JSON option.
    */
  protected def parseIdSearch(jsValue: JsValue): Option[JsonIdSearch] = {
    Try(jsValue.validate[JsonIdSearch]) match {
      case Success(json: JsResult[JsonIdSearch]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Play JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Play JSON option.
    */
  protected def parsePlay(jsValue: JsValue): Option[JsonPlay] = {
    Try(jsValue.validate[JsonPlay]) match {
      case Success(json: JsResult[JsonPlay]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Played-with-token JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Played-with-token JSON option.
    */
  protected def parsePlayedWithToken(jsValue: JsValue): Option[JsonPlayedWithToken] = {
    Try(jsValue.validate[JsonPlayedWithToken]) match {
      case Success(json: JsResult[JsonPlayedWithToken]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Search-result JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Search-result JSON option.
    */
  protected def parseSearchResult(jsValue: JsValue): Option[JsonSearchResult] = {
    Try(jsValue.validate[JsonSearchResult]) match {
      case Success(json: JsResult[JsonSearchResult]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Change-lang JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Change-lang JSON option.
    */
  protected def parseChangeLang(jsValue: JsValue): Option[JsonChangeLang] = {
    Try(jsValue.validate[JsonChangeLang]) match {
      case Success(json: JsResult[JsonChangeLang]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Change-user-email JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Change-user-email JSON option.
    */
  protected def parseChangeUserEmail(jsValue: JsValue): Option[JsonChangeUserEmail] = {
    Try(jsValue.validate[JsonChangeUserEmail]) match {
      case Success(json: JsResult[JsonChangeUserEmail]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Change-user-name JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Change-user-name JSON option.
    */
  protected def parseChangeUserName(jsValue: JsValue): Option[JsonChangeUserName] = {
    Try(jsValue.validate[JsonChangeUserName]) match {
      case Success(json: JsResult[JsonChangeUserName]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Change-user-password JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Change-user-password JSON option.
    */
  protected def parseChangeUserPassword(jsValue: JsValue): Option[JsonChangeUserPassword] = {
    Try(jsValue.validate[JsonChangeUserPassword]) match {
      case Success(json: JsResult[JsonChangeUserPassword]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Get-settings JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Get-settings JSON option.
    */
  protected def parseGetSettings(jsValue: JsValue): Option[JsonGetSettings] = {
    Try(jsValue.validate[JsonGetSettings]) match {
      case Success(json: JsResult[JsonGetSettings]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }
}
