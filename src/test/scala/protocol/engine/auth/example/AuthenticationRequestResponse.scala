package protocol.engine.auth.example

import licos.json.element.auth.server2robot.JsonAuthenticationRequestResponse
import protocol.engine.ServerToRobotAuthExample

final case class AuthenticationRequestResponse(filePath: String) extends ServerToRobotAuthExample(filePath) {
  override val `type`: String = JsonAuthenticationRequestResponse.`type`
}
