package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class Board(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Board.`type`
}

object Board {
  val `type`: String = "village.client2server.Board"
}
