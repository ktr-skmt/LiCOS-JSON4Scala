package licos.protocol.element.lobby.part

import java.util.UUID

import licos.json.element.lobby.JsonPlayerTokenInKickOutPlayer

final case class PlayerTokenInKickOutPlayerProtocol(token: UUID) {

  val json: Option[JsonPlayerTokenInKickOutPlayer] = {
    Some(
      JsonPlayerTokenInKickOutPlayer(
        token.toString
      )
    )
  }

}

object PlayerTokenInKickOutPlayerProtocol {

  def read(json: JsonPlayerTokenInKickOutPlayer): Option[PlayerTokenInKickOutPlayerProtocol] = {
    Some(
      PlayerTokenInKickOutPlayerProtocol(
        UUID.fromString(json.token)
      )
    )
  }

}
