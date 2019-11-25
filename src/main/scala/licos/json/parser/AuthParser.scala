package licos.json.parser

import licos.json.element.auth.robot2server.JsonAuthenticationAndAuthorizationRequest
import licos.json.element.auth.server2robot.JsonAuthenticationAndAuthorizationRequestResponse
import licos.json.engine.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import licos.json.engine.analysis.auth.server2robot.AuthenticationAndAuthorizationRequestResponseAnalysisEngine
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{JsError, JsResult, JsSuccess, JsValue, Json}

import scala.util.{Failure, Success, Try}

@SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
trait AuthParser extends LiCOSParser {

  private val log: Logger = LoggerFactory.getLogger(classOf[AuthParser])

  protected def parseAuthenticationAndAuthorizationRequest(
      jsValue: JsValue
  ): Either[JsValue, JsonAuthenticationAndAuthorizationRequest] = {
    Try(jsValue.validate[JsonAuthenticationAndAuthorizationRequest]) match {
      case Success(json: JsResult[JsonAuthenticationAndAuthorizationRequest]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, AuthenticationAndAuthorizationRequestAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, AuthenticationAndAuthorizationRequestAnalysisEngine.isFromServer))
    }
  }

  protected def parseAuthenticationAndAuthorizationRequestResponse(
      jsValue: JsValue
  ): Either[JsValue, JsonAuthenticationAndAuthorizationRequestResponse] = {
    Try(jsValue.validate[JsonAuthenticationAndAuthorizationRequestResponse]) match {
      case Success(json: JsResult[JsonAuthenticationAndAuthorizationRequestResponse]) =>
        json match {
          case JsSuccess(j, _) => Right(j)
          case e: JsError =>
            log.debug(Json.prettyPrint(JsError.toJson(e)))
            Left(returnError(e, jsValue, AuthenticationAndAuthorizationRequestResponseAnalysisEngine.isFromServer))
        }
      case Failure(err: Throwable) =>
        log.error(err.getMessage)
        Left(returnError(err, jsValue, AuthenticationAndAuthorizationRequestResponseAnalysisEngine.isFromServer))
    }
  }
}
