package licos.protocol.village.server2client

import licos.json.element.village.JsonAnonymousAudienceChat
import licos.state.VillageState

final case class AnonymousAudienceChatFromServerProtocol(state: VillageState, isMine: Boolean, text: String) {

  val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromServerProtocol(state, isMine, text, Nil).json
  }

}
