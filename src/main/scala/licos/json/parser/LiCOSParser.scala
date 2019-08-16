package licos.json.parser

import licos.json.element.lobby.{JsonBuildVillage, JsonLeaveWaitingPage, JsonReady}
import licos.json.lobby.{JsonLeaveWaitingPage, JsonReady}
import play.api.libs.json.{JsResult, JsValue}

import scala.util.{Failure, Success, Try}

/** This JSON parser lets classes extend common parse methods.
  *
  * @author Kotaro Sakamoto
  */
trait LiCOSParser {

  /** Tries to parse play.api.libs.json.JsValue as Ready JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Ready JSON option.
    */
  protected def parseReady(jsValue: JsValue): Option[JsonReady] = {
    Try(jsValue.validate[JsonReady]) match {
      case Success(json: JsResult[JsonReady]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Build-village JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Build-village JSON option.
    */
  protected def parseBuildVillage(jsValue: JsValue): Option[JsonBuildVillage] = {
    Try(jsValue.validate[JsonBuildVillage]) match {
      case Success(json: JsResult[JsonBuildVillage]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Leave-waiting-page JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a Leave-waiting-page JSON option.
    */
  protected def parseLeaveWaitingPage(jsValue: JsValue): Option[JsonLeaveWaitingPage] = {
    Try(jsValue.validate[JsonLeaveWaitingPage]) match {
      case Success(json: JsResult[JsonLeaveWaitingPage]) => json.asOpt
      case Failure(err: Throwable) =>
        System.err.println(err.getMessage)
        None
    }
  }
}
