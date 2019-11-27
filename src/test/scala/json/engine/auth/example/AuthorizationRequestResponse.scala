package json.engine.auth.example

import json.engine.ServerToRobotAuthExample

final case class AuthorizationRequestResponse(filePath: String) extends ServerToRobotAuthExample(filePath) {
  override val `type`: String = AuthorizationRequestResponse.`type`
}

object AuthorizationRequestResponse {
  val `type`: String = "AuthorizationRequestResponse"
}
