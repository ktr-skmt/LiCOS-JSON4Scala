package licos.json.parser

import licos.json.element.lobby.client2server.{JsonBuildVillage, JsonLeaveWaitingPage, JsonReady}
import licos.json.element.village.{JsonName, JsonSubError}
import licos.json.engine.analysis.lobby.client2server.{
  BuildVillageAnalysisEngine,
  LeaveWaitingPageAnalysisEngine,
  ReadyAnalysisEngine
}
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{JsError, JsResult, JsSuccess, JsValue, Json}

import scala.util.{Failure, Success, Try}

/** This JSON parser lets classes extend common parse methods.
  *
  * @author Kotaro Sakamoto
  */
@SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
trait LiCOSParser {

  private final val log: Logger = LoggerFactory.getLogger(classOf[LiCOSParser])

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  private def returnError(name: JsonName, errorMessage: String, sourceCode: JsValue, isFromServer: Boolean): JsValue = {
    Json.toJson(
      new JsonSubError(
        name,
        "warning",
        s"""$errorMessage
         |$sourceCode
         |""".stripMargin,
        isFromServer = true
      )
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  protected def returnError(e: JsError, sourceCode: JsValue, isFromServer: Boolean): JsValue = {
    returnError(
      new JsonName(
        en = "JsError",
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None
      ),
      Json.prettyPrint(JsError.toJson(e)),
      sourceCode,
      isFromServer
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  protected def returnError(e: Throwable, sourceCode: JsValue, isFromServer: Boolean): JsValue = {
    returnError(
      new JsonName(
        en = "Throwable",
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None
      ),
      e.getMessage,
      sourceCode,
      isFromServer
    )
  }

  /** Tries to parse play.api.libs.json.JsValue as Ready JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Ready JSON.
    */
  protected def parseReady(jsValue: JsValue): Either[JsValue, JsonReady] = {
    Try(jsValue.validate[JsonReady]) match {
      case Success(json: JsResult[JsonReady]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, ReadyAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, ReadyAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Build-village JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Build-village JSON.
    */
  protected def parseBuildVillage(jsValue: JsValue): Either[JsValue, JsonBuildVillage] = {
    Try(jsValue.validate[JsonBuildVillage]) match {
      case Success(json: JsResult[JsonBuildVillage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, BuildVillageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, BuildVillageAnalysisEngine.isFromServer))
    }
  }

  /** Tries to parse play.api.libs.json.JsValue as Leave-waiting-page JSON.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return either Leave-waiting-page JSON.
    */
  protected def parseLeaveWaitingPage(jsValue: JsValue): Either[JsValue, JsonLeaveWaitingPage] = {
    Try(jsValue.validate[JsonLeaveWaitingPage]) match {
      case Success(json: JsResult[JsonLeaveWaitingPage]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, LeaveWaitingPageAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, LeaveWaitingPageAnalysisEngine.isFromServer))
    }
  }
}
