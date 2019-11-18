package licos.protocol.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat

final case class OnymousAudienceChatFromServerProtocol(village: Village, isMine: Boolean, text: String) {

  val json: Option[JsonOnymousAudienceChat] = {
    server2logger.OnymousAudienceChatFromServerProtocol(village, isMine, text, Nil).json
  }

}
