package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat
import licos.protocol.element.village.VillageMessageProtocol

final case class OnymousAudienceChatFromServerProtocol(village: Village, isMine: Boolean, text: String)
    extends VillageMessageProtocol {

  val json: Option[JsonOnymousAudienceChat] = {
    server2logger.OnymousAudienceChatFromServerProtocol(village, isMine, text, Nil).json
  }

}

object OnymousAudienceChatFromServerProtocol {

  def read(json: JsonOnymousAudienceChat): Option[OnymousAudienceChatFromServerProtocol] = {

  }

}
