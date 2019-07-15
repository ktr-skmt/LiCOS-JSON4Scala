package engine.village.example

import engine.Example

case class GameResult(filePath: String) extends Example(filePath) {
  override val `type`: String = GameResult.`type`
}

object GameResult {
  val `type`: String = "GameResult"
}