package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonFlavorText
import licos.protocol.element.village.VillageMessageProtocol

final case class FlavorTextProtocol(village: Village, flavorText: Seq[ChatFromServerProtocol]) extends VillageMessageProtocol {

  val json: Option[JsonFlavorText] = {
    server2logger.FlavorTextProtocol(village, flavorText, Nil).json
  }

}

object FlavorTextProtocol {

  def read(json: JsonFlavorText, village: Village): Option[FlavorTextProtocol] = {

  }

}
