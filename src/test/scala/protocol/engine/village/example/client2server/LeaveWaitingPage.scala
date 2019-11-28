package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class LeaveWaitingPage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = LeaveWaitingPage.`type`
}

object LeaveWaitingPage {
  val `type`: String = "village.client2server.LeaveWaitingPage"
}
