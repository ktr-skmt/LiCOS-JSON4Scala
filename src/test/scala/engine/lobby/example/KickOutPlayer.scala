package engine.lobby.example

import engine.Example

case class KickOutPlayer(filePath: String) extends Example(filePath) {
  override val `type`: String = KickOutPlayer.`type`
}

object KickOutPlayer {
  val `type`: String = "KickOutPlayer"
}