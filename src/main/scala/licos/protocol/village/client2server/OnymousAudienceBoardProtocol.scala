package licos.protocol.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat

final case class OnymousAudienceBoardProtocol(village: Village, text: String) {

  val json: Option[JsonOnymousAudienceChat] = {
    server2logger.OnymousAudienceBoardProtocol(village, text, Nil).json
  }

}
