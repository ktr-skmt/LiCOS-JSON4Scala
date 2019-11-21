package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceBoard
import licos.knowledge.{Character, PolarityMark, Role}
import licos.protocol.element.village.VillageMessageProtocol

final case class OnymousAudienceBoardProtocol(village:    Village,
                                              character:  Character,
                                              role:       Role,
                                              prediction: PolarityMark)
    extends VillageMessageProtocol {

  val json: Option[JsonOnymousAudienceBoard] = {
    server2logger.OnymousAudienceBoardProtocol(village, character, role, prediction, Nil).json
  }

}

object OnymousAudienceBoardProtocol {

  def read(json: JsonOnymousAudienceBoard, village: Village): Option[OnymousAudienceBoardProtocol] = {
    Some(
      OnymousAudienceBoardProtocol(

      )
    )
  }

}
