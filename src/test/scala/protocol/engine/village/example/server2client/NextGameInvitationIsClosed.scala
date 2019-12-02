package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class NextGameInvitationIsClosed(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = NextGameInvitationIsClosed.`type`
}

object NextGameInvitationIsClosed {
  val `type`: String = "village.server2client.NextGameInvitationIsClosed"
}
