package engine.lobby.example

import engine.ServerToClientLobbyExample

case class PlayedWithToken(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = PlayedWithToken.`type`
}

object PlayedWithToken {
  val `type`: String = "PlayedWithToken"
}