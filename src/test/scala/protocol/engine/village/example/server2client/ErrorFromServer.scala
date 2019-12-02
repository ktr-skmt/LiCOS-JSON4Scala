package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class ErrorFromServer(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = ErrorFromServer.`type`
}

object ErrorFromServer {
  val `type`: String = "village.server2client.ErrorFromServer"
}
