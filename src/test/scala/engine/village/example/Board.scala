package engine.village.example

import engine.ClientToServerVillageExample

case class Board(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Board.`type`
}

object Board {
  val `type`: String = "Board"
}