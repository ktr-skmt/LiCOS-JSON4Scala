package licos.protocol.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat

final case class AnonymousAudienceChatFromClientProtocol(village: Village, text: String) {

  val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromClientProtocol(village, text, Nil).json
  }

}
