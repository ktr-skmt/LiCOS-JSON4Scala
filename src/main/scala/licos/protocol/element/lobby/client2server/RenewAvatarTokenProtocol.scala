package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonRenewAvatarToken
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class RenewAvatarTokenProtocol(token: UUID, lobby: Lobby) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonRenewAvatarToken] = Some(new JsonRenewAvatarToken(token.toString, lobby.label))

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object RenewAvatarTokenProtocol {

  def read(json: JsonRenewAvatarToken): Option[RenewAvatarTokenProtocol] = {
    Data2Knowledge.lobbyOpt(json.lobby).map { lobby: Lobby =>
      RenewAvatarTokenProtocol(
        UUID.fromString(json.token),
        lobby
      )
    }
  }

}
