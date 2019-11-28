package json.engine.auth.example.robot2server

import json.engine.RobotToServerAuthExample

final case class AuthenticationAndAuthorizationRequest(filePath: String) extends RobotToServerAuthExample(filePath) {
  override val `type`: String = AuthenticationAndAuthorizationRequest.`type`
}

object AuthenticationAndAuthorizationRequest {
  val `type`: String = "AuthenticationAndAuthorizationRequest"
}
