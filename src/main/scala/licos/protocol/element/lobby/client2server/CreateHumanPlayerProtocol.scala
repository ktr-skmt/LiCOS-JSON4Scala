package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.client2server.JsonCreateHumanPlayer
import play.api.libs.json.{JsValue, Json}

final case class CreateHumanPlayerProtocol(name: String) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonCreateHumanPlayer] = {
    Some(
      new JsonCreateHumanPlayer(
        name
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object CreateHumanPlayerProtocol {

  def read(json: JsonCreateHumanPlayer): Option[CreateHumanPlayerProtocol] = {
    Some(
      CreateHumanPlayerProtocol(
        json.name
      )
    )
  }

}
