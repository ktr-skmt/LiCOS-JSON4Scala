package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonRunRobotPlayerInTheForeground
import play.api.libs.json.{JsValue, Json}

final case class RunRobotPlayerInTheForegroundProtocol(token: UUID) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonRunRobotPlayerInTheForeground] = {
    Some(
      new JsonRunRobotPlayerInTheForeground(
        token.toString
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object RunRobotPlayerInTheForegroundProtocol {

  def read(json: JsonRunRobotPlayerInTheForeground): Option[RunRobotPlayerInTheForegroundProtocol] = {
    Some(
      RunRobotPlayerInTheForegroundProtocol(
        UUID.fromString(json.token)
      )
    )
  }

}
