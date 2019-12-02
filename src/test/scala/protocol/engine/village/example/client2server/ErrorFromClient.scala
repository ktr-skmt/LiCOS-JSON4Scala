package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class ErrorFromClient(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = ErrorFromClient.`type`
}

object ErrorFromClient {
  val `type`: String = "village.client2server.ErrorFromClient"
}
