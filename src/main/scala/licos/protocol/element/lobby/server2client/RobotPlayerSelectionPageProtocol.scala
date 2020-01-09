package licos.protocol.element.lobby.server2client

import licos.json.element.lobby.server2client.JsonRobotPlayerSelectionPage
import licos.protocol.element.lobby.part.RobotPlayerInfoProtocol
import play.api.libs.json.{JsValue, Json}

final case class RobotPlayerSelectionPageProtocol(avatar: Seq[RobotPlayerInfoProtocol])
    extends Server2ClientLobbyMessageProtocol {

  private lazy val json: Option[JsonRobotPlayerSelectionPage] = {
    Some(
      new JsonRobotPlayerSelectionPage(
        avatar.flatMap(_.json.toList)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object RobotPlayerSelectionPageProtocol {
  def read(json: JsonRobotPlayerSelectionPage): Option[RobotPlayerSelectionPageProtocol] = {
    Some(
      RobotPlayerSelectionPageProtocol(
        json.avatar.flatMap(j => RobotPlayerInfoProtocol.read(j).toList)
      )
    )
  }
}
