package licos.protocol.village.server2client

import licos.json.element.village.JsonOnymousAudienceChat
import licos.state.VillageState

final case class OnymousAudienceChatFromServerProtocol(state:  VillageState,
                                                       isMine: Boolean,
                                                       text:   String) {

  val json: Option[JsonOnymousAudienceChat] = {
    server2logger.OnymousAudienceChatFromServerProtocol(state, isMine, text, Nil).json
  }

}
