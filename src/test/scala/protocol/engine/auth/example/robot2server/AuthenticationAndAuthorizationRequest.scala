package protocol.engine.auth.example.robot2server

import protocol.engine.RobotToServerAuthExample

final case class AuthenticationAndAuthorizationRequest(filePath: String) extends RobotToServerAuthExample(filePath) {
  override val `type`: String = AuthenticationAndAuthorizationRequest.`type`
}

object AuthenticationAndAuthorizationRequest {
  val `type`: String = "auth.robot2server.AuthenticationAndAuthorizationRequest"
}
