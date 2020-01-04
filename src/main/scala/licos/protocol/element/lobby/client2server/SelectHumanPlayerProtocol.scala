package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonSelectHumanPlayer
import play.api.libs.json.{JsValue, Json}

final case class SelectHumanPlayerProtocol(token: UUID) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonSelectHumanPlayer] = {
    Some(
      new JsonSelectHumanPlayer(
        token.toString
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

}

object SelectHumanPlayerProtocol {

  def read(json: JsonSelectHumanPlayer): Option[SelectHumanPlayerProtocol] = {
    Some(
      SelectHumanPlayerProtocol(
        UUID.fromString(json.token)
      )
    )
  }

}
