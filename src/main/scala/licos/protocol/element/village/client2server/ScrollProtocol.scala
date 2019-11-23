package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonScroll

final case class ScrollProtocol(
    village:      Village,
    nodeId:       String,
    scrollTop:    Int,
    scrollHeight: Int,
    offsetHeight: Int
) extends Client2ServerVillageMessageProtocol {

  val json: Option[JsonScroll] = {
    server2logger.ScrollProtocol(village, nodeId, scrollTop, scrollHeight, offsetHeight, Nil).json
  }

}

object ScrollProtocol {

  def read(json: JsonScroll, village: Village): Option[ScrollProtocol] = {
    Some(
      ScrollProtocol(
        village,
        json.nodeId,
        json.scrollTop,
        json.scrollHeight,
        json.offsetHeight
      )
    )
  }

}
