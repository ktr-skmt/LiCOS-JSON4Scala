package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class ChangeLang(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeLang.`type`
}

object ChangeLang {
  val `type`: String = "lobby.client2server.ChangeLang"
}
