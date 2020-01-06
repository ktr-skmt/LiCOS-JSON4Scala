package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonRunRobotPlayerInTheBackground
import play.api.libs.json.{JsValue, Json}

final case class RunRobotPlayerInTheBackgroundProtocol(token: UUID) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonRunRobotPlayerInTheBackground] = {
    Some(
      new JsonRunRobotPlayerInTheBackground(
        token.toString
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

}

object RunRobotPlayerInTheBackgroundProtocol {

  def read(json: JsonRunRobotPlayerInTheBackground): Option[RunRobotPlayerInTheBackgroundProtocol] = {
    Some(
      RunRobotPlayerInTheBackgroundProtocol(
        UUID.fromString(json.token)
      )
    )
  }

}
