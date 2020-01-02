package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class NewAvatarToken(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = NewAvatarToken.`type`
}

object NewAvatarToken {
  val `type`: String = "NewAvatarToken"
}
