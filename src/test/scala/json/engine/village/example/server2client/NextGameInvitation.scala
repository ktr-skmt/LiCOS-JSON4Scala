package json.engine.village.example.server2client

import json.engine.ServerToClientVillageExample

final case class NextGameInvitation(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = NextGameInvitation.`type`
}

object NextGameInvitation {
  val `type`: String = "NextGameInvitation"
}
