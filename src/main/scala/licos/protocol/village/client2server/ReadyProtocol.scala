package licos.protocol.village.client2server

import licos.entity.Village
import licos.json.element.lobby.JsonReady

final case class ReadyProtocol(village: Village) {

  val json: Option[JsonReady] = {
    if (village.isAvailable) {
      Option(
        new JsonReady(
          village.tokenOpt.get.toString,
          village.id
        )
      )
    } else {
      None
    }
  }

}
