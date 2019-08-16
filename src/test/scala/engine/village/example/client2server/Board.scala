package engine.village.example.client2server

import engine.ClientToServerVillageExample

case class Board(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Board.`type`
}

object Board {
  val `type`: String = "Board"
}