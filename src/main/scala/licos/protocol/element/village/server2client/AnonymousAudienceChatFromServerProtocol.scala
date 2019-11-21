package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.protocol.element.village.VillageMessageProtocol

final case class AnonymousAudienceChatFromServerProtocol(village: Village, isMine: Boolean, text: String)
    extends VillageMessageProtocol {

  val json: Option[JsonAnonymousAudienceChat] = {
    server2logger.AnonymousAudienceChatFromServerProtocol(village, isMine, text, Nil).json
  }

}

object AnonymousAudienceChatFromServerProtocol {

  def read(json: JsonAnonymousAudienceChat, village: Village): Option[AnonymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {
      Some(AnonymousAudienceChatFromServerProtocol(
        village,
        json.isMine,
        json.text.`@value`
      ))
    } else {
      None
    }
  }

}
