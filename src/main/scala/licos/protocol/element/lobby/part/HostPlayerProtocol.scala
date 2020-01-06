package licos.protocol.element.lobby.part

import licos.json.element.lobby.JsonHostPlayer

final case class HostPlayerProtocol(name: String, isAnonymous: Boolean, isHuman: Boolean) {

  lazy val json: Option[JsonHostPlayer] = {
    Some(
      JsonHostPlayer(
        name,
        isAnonymous,
        isHuman
      )
    )
  }

}

object HostPlayerProtocol {

  def read(json: JsonHostPlayer): Option[HostPlayerProtocol] = {
    Some(
      HostPlayerProtocol(
        json.name,
        json.isAnonymous,
        json.isHuman
      )
    )
  }

}
