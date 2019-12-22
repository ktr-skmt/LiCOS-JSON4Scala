package licos.json2protocol.auth

import licos.json.element.auth.robot2server.JsonAuthenticationAndAuthorizationRequest
import licos.json.element.auth.server2robot.{JsonAuthenticationRequestResponse, JsonAuthorizationRequestResponse}
import licos.json.flow.{AuthFlowController, FlowController}
import licos.json2protocol.Json2Protocol
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.robot2server.AuthenticationAndAuthorizationRequestProtocol
import licos.protocol.element.auth.server2robot.{
  AuthenticationRequestResponseProtocol,
  AuthorizationRequestResponseProtocol
}
import play.api.libs.json.JsValue

object Json2AuthMessageProtocol extends Json2Protocol {

  override protected val flowController: FlowController = new AuthFlowController()

  def toProtocolOpt(json: JsValue): Option[AuthMessageProtocol] = {
    flowController.flow(json) match {
      case Right(json: JsonAuthenticationAndAuthorizationRequest) =>
        AuthenticationAndAuthorizationRequestProtocol.read(json)
      case Right(json: JsonAuthenticationRequestResponse) =>
        AuthenticationRequestResponseProtocol.read(json)
      case Right(json: JsonAuthorizationRequestResponse) =>
        AuthorizationRequestResponseProtocol.read(json)
      case _ => None
    }
  }
}
