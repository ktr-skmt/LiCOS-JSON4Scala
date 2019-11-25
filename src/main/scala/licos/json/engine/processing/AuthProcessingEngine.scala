package licos.json.engine.processing

import com.typesafe.scalalogging.Logger
import licos.json.element.auth.robot2server.JsonAuthenticationAndAuthorizationRequest
import licos.json.element.auth.server2robot.JsonAuthenticationAndAuthorizationRequestResponse
import licos.json.element.village.{JsonName, JsonSubError}
import licos.json.engine.BOX
import licos.json.engine.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import licos.json.engine.analysis.auth.server2robot.AuthenticationAndAuthorizationRequestResponseAnalysisEngine
import licos.json.flow.AuthFlowController
import play.api.libs.json.{JsValue, Json}

class AuthProcessingEngine(
    authenticationAndAuthorizationRequestEngine: Option[AuthenticationAndAuthorizationRequestAnalysisEngine],
    authenticationAndAuthorizationRequestResponseEngine: Option[
      AuthenticationAndAuthorizationRequestResponseAnalysisEngine
    ]
) extends ProcessingEngine {

  override protected val flowController = new AuthFlowController()

  private final val logger = Logger[LobbyProcessingEngine]

  override def process(box: BOX, msg: String): Either[JsValue, JsValue] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
    def noAnalysisEngine(name: String, isFromServer: Boolean): Either[JsValue, JsValue] = {
      Left(
        Json.toJson(
          new JsonSubError(
            new JsonName(
              en = s"No $name is set. Please set it to the processing engine.",
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
            "warning",
            "Nothing",
            isFromServer
          )
        )
      )
    }

    @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
    def otherwise: Either[JsValue, JsValue] = {
      Right(
        Json.toJson(
          new JsonSubError(
            new JsonName(
              en = "AuthProcessingEngine returns nothing",
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
            "warning",
            jsValue.toString,
            isFromServer = true
          )
        )
      )
    }

    flowController.flow(jsValue) match {
      case Right(authenticationAndAuthorizationRequest: JsonAuthenticationAndAuthorizationRequest) =>
        log("JsonAuthenticationAndAuthorizationRequest")
        authenticationAndAuthorizationRequestEngine match {
          case Some(engine) =>
            engine.process(box, authenticationAndAuthorizationRequest)
          case None =>
            noAnalysisEngine(
              AuthenticationAndAuthorizationRequestAnalysisEngine.name,
              AuthenticationAndAuthorizationRequestAnalysisEngine.isFromServer
            )
        }
      case Right(authenticationAndAuthorizationRequestResponse: JsonAuthenticationAndAuthorizationRequestResponse) =>
        log("JsonAuthenticationAndAuthorizationRequestResponse")
        authenticationAndAuthorizationRequestResponseEngine match {
          case Some(engine) =>
            engine.process(box, authenticationAndAuthorizationRequestResponse)
          case None =>
            noAnalysisEngine(
              AuthenticationAndAuthorizationRequestResponseAnalysisEngine.name,
              AuthenticationAndAuthorizationRequestResponseAnalysisEngine.isFromServer
            )
        }
      case _ =>
        log("return nothing")
        otherwise
    }
  }
}
