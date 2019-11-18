package licos.protocol.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat

final case class AnonymousAudienceChatFromServerProtocol(village: Village, isMine: Boolean, text: String) {

  val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromServerProtocol(village, isMine, text, Nil).json
  }

}
