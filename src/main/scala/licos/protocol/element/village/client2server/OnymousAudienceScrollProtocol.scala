package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceScroll
import licos.protocol.element.village.VillageMessageProtocol

final case class OnymousAudienceScrollProtocol(village:      Village,
                                               nodeId:       String,
                                               scrollTop:    Int,
                                               scrollHeight: Int,
                                               offsetHeight: Int) extends VillageMessageProtocol {

  val json: Option[JsonOnymousAudienceScroll] = {
    server2logger.OnymousAudienceScrollProtocol(village, nodeId, scrollTop, scrollHeight, offsetHeight, Nil).json
  }

}

object OnymousAudienceScrollProtocol {

  def read(json: JsonOnymousAudienceScroll, village: Village): Option[OnymousAudienceScrollProtocol] = {
    Some(
      OnymousAudienceScrollProtocol(

      )
    )
  }

}
