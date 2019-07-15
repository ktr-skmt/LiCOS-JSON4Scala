package engine.lobby.example

import engine.Example

case class Lobby(filePath: String) extends Example(filePath) {
  override val `type`: String = Lobby.`type`
}

object Lobby {
  val `type`: String = "Lobby"
}