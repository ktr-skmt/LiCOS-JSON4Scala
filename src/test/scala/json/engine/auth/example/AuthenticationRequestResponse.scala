package json.engine.auth.example

import json.engine.ServerToRobotAuthExample

final case class AuthenticationRequestResponse(filePath: String) extends ServerToRobotAuthExample(filePath) {
  override val `type`: String = AuthenticationRequestResponse.`type`
}

object AuthenticationRequestResponse {
  val `type`: String = "AuthenticationRequestResponse"
}
