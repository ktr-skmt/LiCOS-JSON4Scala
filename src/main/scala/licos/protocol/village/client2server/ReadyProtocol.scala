package licos.protocol.village.client2server

import licos.json.element.lobby.JsonReady
import licos.state.VillageState

final case class ReadyProtocol(state: VillageState) {

  val json: Option[JsonReady] = {
    if (state.isAvailable) {
      Option(new JsonReady(

      ))
    } else {
      None
    }
  }

}
