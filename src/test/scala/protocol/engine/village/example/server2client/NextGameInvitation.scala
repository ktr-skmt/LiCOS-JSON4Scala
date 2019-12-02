package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class NextGameInvitation(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = NextGameInvitation.`type`
}

object NextGameInvitation {
  val `type`: String = "village.server2client.NextGameInvitation"
}
