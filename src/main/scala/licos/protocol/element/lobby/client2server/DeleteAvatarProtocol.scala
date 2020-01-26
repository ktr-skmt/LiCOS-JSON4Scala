package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonDeleteAvatar
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class DeleteAvatarProtocol(token: Seq[UUID], lobby: Lobby) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonDeleteAvatar] = {
    Some(
      new JsonDeleteAvatar(
        token
          .map(_.toString),
        lobby.label
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object DeleteAvatarProtocol {

  def read(json: JsonDeleteAvatar): Option[DeleteAvatarProtocol] = {
    Data2Knowledge.lobbyOpt(json.lobby).map { lobby: Lobby =>
      DeleteAvatarProtocol(
        json.token
          .map(UUID.fromString),
        lobby
      )
    }
  }

}
