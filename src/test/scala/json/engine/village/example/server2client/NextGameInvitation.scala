package json.engine.village.example.server2client

import json.engine.ServerToClientVillageExample

case class NextGameInvitation(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = NextGameInvitation.`type`
}

object NextGameInvitation {
  val `type`: String = "NextGameInvitation"
}
