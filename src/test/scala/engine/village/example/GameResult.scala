package engine.village.example

import engine.ServerToClientVillageExample

case class GameResult(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = GameResult.`type`
}

object GameResult {
  val `type`: String = "GameResult"
}