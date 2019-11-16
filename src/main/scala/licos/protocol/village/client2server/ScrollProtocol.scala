package licos.protocol.village.client2server

import licos.json.element.village.JsonScroll
import licos.state.VillageState

final case class ScrollProtocol(state:        VillageState,
                                nodeId:       String,
                                scrollTop:    Int,
                                scrollHeight: Int,
                                offsetHeight: Int) {

  val json: Option[JsonScroll] = {
    server2logger.ScrollProtocol(state, nodeId, scrollTop, scrollHeight, offsetHeight, Nil).json
  }

}
