package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.protocol.element.village.VillageMessageProtocol

final case class AnonymousAudienceChatFromClientProtocol(village: Village, text: String) extends VillageMessageProtocol {

  val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromClientProtocol(village, text, Nil).json
  }

}

object AnonymousAudienceChatFromClientProtocol {

  def read(json: JsonAnonymousAudienceChat, village: Village): Option[AnonymousAudienceChatFromClientProtocol] = {
    if (!json.isFromServer) {
      Some(
        AnonymousAudienceChatFromClientProtocol(
          village,
          json.text.`@value`
        )
      )
    } else {
      None
    }
  }

}
