package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat
import licos.protocol.element.village.VillageMessageProtocol

final case class OnymousAudienceChatFromClientProtocol(village: Village, text: String) extends VillageMessageProtocol {

  val json: Option[JsonOnymousAudienceChat] = {
    server2logger.OnymousAudienceChatFromClientProtocol(village, text, Nil).json
  }

}

object OnymousAudienceChatFromClientProtocol {

  def read(json: JsonOnymousAudienceChat): Option[OnymousAudienceChatFromClientProtocol] = {
    if (!json.isFromServer) {
      Option(OnymousAudienceChatFromClientProtocol(

      ))
    } else {
      None
    }
  }

}
