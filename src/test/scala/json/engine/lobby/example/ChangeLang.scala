package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

final case class ChangeLang(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeLang.`type`
}

object ChangeLang {
  val `type`: String = "ChangeLang"
}
