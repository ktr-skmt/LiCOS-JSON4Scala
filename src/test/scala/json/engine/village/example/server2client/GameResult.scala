package json.engine.village.example.server2client

import json.engine.ServerToClientVillageExample

final case class GameResult(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = GameResult.`type`
}

object GameResult {
  val `type`: String = "GameResult"
}
