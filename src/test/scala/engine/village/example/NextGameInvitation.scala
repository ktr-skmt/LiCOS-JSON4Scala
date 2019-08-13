package engine.village.example

import engine.ServerToClientVillageExample

case class NextGameInvitation(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = NextGameInvitation.`type`
}

object NextGameInvitation {
  val `type`: String = "NextGameInvitation"
}
