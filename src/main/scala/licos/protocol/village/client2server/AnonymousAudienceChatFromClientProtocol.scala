package licos.protocol.village.client2server

import licos.json.element.village.JsonAnonymousAudienceChat
import licos.state.VillageState

final case class AnonymousAudienceChatFromClientProtocol(state: VillageState, text: String) {

  val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromClientProtocol(state, text, Nil).json
  }

}
