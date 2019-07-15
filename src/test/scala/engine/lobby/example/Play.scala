package engine.lobby.example

import engine.Example

case class Play(filePath: String) extends Example(filePath) {
  override val `type`: String = Play.`type`
}

object Play {
  val `type`: String = "Play"
}