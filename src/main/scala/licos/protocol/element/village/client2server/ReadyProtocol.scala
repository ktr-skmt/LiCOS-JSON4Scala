package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.lobby.JsonReady
import licos.protocol.element.village.VillageMessageProtocol

final case class ReadyProtocol(village: Village) extends VillageMessageProtocol {

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

object ReadyProtocol {

  def read(json: JsonReady, village: Village): Option[ReadyProtocol] = {
    Some(ReadyProtocol(village))
  }

}
