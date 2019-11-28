package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonKickOutPlayer
import protocol.engine.ClientToServerLobbyExample

final case class KickOutPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonKickOutPlayer.`type`
}
