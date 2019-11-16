package licos.protocol.village.client2server

import licos.json.element.lobby.JsonLeaveWaitingPage
import licos.state.VillageState

final case class LeaveWaitingPageProtocol(state: VillageState) {

  val json: Option[JsonLeaveWaitingPage] = {
    if (state.isAvailable) {
      Option(new JsonLeaveWaitingPage(

      ))
    } else {
      None
    }
  }

}
