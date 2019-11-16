package licos.protocol.village.client2server

import licos.json.element.village.JsonOnymousAudienceChat
import licos.state.VillageState

final case class OnymousAudienceChatFromClientProtocol(state: VillageState, text: String) {

  val json: Option[JsonOnymousAudienceChat] = {
    server2logger.OnymousAudienceChatFromClientProtocol(state, text, Nil).json
  }

}
