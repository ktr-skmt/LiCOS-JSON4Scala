package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class GameResult(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = GameResult.`type`
}

object GameResult {
  val `type`: String = "village.server2client.GameResult"
}
