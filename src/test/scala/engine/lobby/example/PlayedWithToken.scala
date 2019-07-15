package engine.lobby.example

import engine.Example

case class PlayedWithToken(filePath: String) extends Example(filePath) {
  override val `type`: String = PlayedWithToken.`type`
}

object PlayedWithToken {
  val `type`: String = "PlayedWithToken"
}