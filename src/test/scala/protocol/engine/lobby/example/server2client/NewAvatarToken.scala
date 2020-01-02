package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class NewAvatarToken(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = NewAvatarToken.`type`
}

object NewAvatarToken {
  val `type`: String = "lobby.server2client.NewAvatarToken"
}
