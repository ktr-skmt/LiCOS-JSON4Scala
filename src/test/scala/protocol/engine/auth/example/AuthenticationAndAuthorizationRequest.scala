package protocol.engine.auth.example

import licos.json.element.auth.robot2server.JsonAuthenticationAndAuthorizationRequest
import protocol.engine.RobotToServerAuthExample

final case class AuthenticationAndAuthorizationRequest(filePath: String) extends RobotToServerAuthExample(filePath) {
  override val `type`: String = JsonAuthenticationAndAuthorizationRequest.`type`
}
