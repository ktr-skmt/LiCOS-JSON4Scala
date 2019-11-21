package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonScroll
import licos.protocol.element.village.VillageMessageProtocol

final case class ScrollProtocol(
    village:      Village,
    nodeId:       String,
    scrollTop:    Int,
    scrollHeight: Int,
    offsetHeight: Int
) extends VillageMessageProtocol {

  val json: Option[JsonScroll] = {
    server2logger.ScrollProtocol(village, nodeId, scrollTop, scrollHeight, offsetHeight, Nil).json
  }

}

object ScrollProtocol {

  def read(json: JsonScroll): Option[ScrollProtocol] = {

  }

}
