package protocol.engine.auth.example.server2robot

import protocol.engine.ServerToRobotAuthExample

final case class AuthorizationRequestResponse(filePath: String) extends ServerToRobotAuthExample(filePath) {
  override val `type`: String = AuthorizationRequestResponse.`type`
}

object AuthorizationRequestResponse {
  val `type`: String = "auth.server2robot.AuthorizationRequestResponse"
}
