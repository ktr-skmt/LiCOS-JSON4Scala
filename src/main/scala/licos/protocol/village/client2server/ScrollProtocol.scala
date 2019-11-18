package licos.protocol.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonScroll

final case class ScrollProtocol(
    village:      Village,
    nodeId:       String,
    scrollTop:    Int,
    scrollHeight: Int,
    offsetHeight: Int
) {

  val json: Option[JsonScroll] = {
    server2logger.ScrollProtocol(village, nodeId, scrollTop, scrollHeight, offsetHeight, Nil).json
  }

}
