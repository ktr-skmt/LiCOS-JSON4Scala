package protocol.engine.auth.example.server2robot

import protocol.engine.ServerToRobotAuthExample

final case class AuthenticationRequestResponse(filePath: String) extends ServerToRobotAuthExample(filePath) {
  override val `type`: String = AuthenticationRequestResponse.`type`
}

object AuthenticationRequestResponse {
  val `type`: String = "auth.server2robot.AuthenticationRequestResponse"
}
