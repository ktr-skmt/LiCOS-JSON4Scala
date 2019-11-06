package engine.lobby.example

import engine.ClientToServerLobbyExample

case class ChangeLang(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeLang.`type`
}

object ChangeLang {
  val `type`: String = "ChangeLang"
}
