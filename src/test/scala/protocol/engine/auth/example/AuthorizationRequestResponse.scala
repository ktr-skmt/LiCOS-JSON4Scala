package protocol.engine.auth.example

import licos.json.element.auth.server2robot.JsonAuthorizationRequestResponse
import protocol.engine.ServerToRobotAuthExample

final case class AuthorizationRequestResponse(filePath: String) extends ServerToRobotAuthExample(filePath) {
  override val `type`: String = JsonAuthorizationRequestResponse.`type`
}
