package engine.village.example

import engine.ServerToClientVillageExample

case class NextGameInvitationIsClosed(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = NextGameInvitationIsClosed.`type`
}

object NextGameInvitationIsClosed {
  val `type`: String = "NextGameInvitationIsClosed"
}