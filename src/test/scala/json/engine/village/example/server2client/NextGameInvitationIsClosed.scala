package json.engine.village.example.server2client

import json.engine.ServerToClientVillageExample

final case class NextGameInvitationIsClosed(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = NextGameInvitationIsClosed.`type`
}

object NextGameInvitationIsClosed {
  val `type`: String = "NextGameInvitationIsClosed"
}
