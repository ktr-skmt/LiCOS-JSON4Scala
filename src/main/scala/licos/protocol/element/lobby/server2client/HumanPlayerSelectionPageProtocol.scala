package licos.protocol.element.lobby.server2client

import licos.json.element.lobby.server2client.JsonHumanPlayerSelectionPage
import play.api.libs.json.{JsValue, Json}

final case class HumanPlayerSelectionPageProtocol(avatar: Seq[AvatarInfoProtocol])
    extends Server2ClientLobbyMessageProtocol {

  private lazy val json: Option[JsonHumanPlayerSelectionPage] = {
    Some(
      new JsonHumanPlayerSelectionPage(
        avatar.flatMap(_.jsonWithoutType.toList)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object HumanPlayerSelectionPageProtocol {
  def read(json: JsonHumanPlayerSelectionPage): Option[HumanPlayerSelectionPageProtocol] = {

    Some(
      HumanPlayerSelectionPageProtocol(
        json.avatar.flatMap(j => AvatarInfoProtocol.read(j).toList)
      )
    )
  }
}
