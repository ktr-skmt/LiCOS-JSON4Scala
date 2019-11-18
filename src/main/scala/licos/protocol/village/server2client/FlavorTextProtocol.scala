package licos.protocol.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonFlavorText

final case class FlavorTextProtocol(village: Village, flavorText: Seq[ChatFromServerProtocol]) {

  val json: Option[JsonFlavorText] = {
    server2logger.FlavorTextProtocol(village, flavorText, Nil).json
  }

}
