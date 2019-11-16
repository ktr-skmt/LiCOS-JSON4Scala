package licos.protocol.village.server2client

import licos.json.element.village.JsonFlavorText
import licos.state.VillageState

final case class FlavorTextProtocol(state: VillageState, flavorText: Seq[ChatFromServerProtocol]) {

  val json: Option[JsonFlavorText] = {
    server2logger.FlavorTextProtocol(state, flavorText, Nil).json
  }

}
