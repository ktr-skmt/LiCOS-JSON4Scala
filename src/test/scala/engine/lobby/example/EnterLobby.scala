package engine.lobby.example

import engine.Example

case class EnterLobby(filePath: String) extends Example(filePath) {
  override val `type`: String = EnterLobby.`type`
}

object EnterLobby {
  val `type`: String = "EnterLobby"
}