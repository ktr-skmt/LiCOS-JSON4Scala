package protocol.engine.village.example.server2client

import protocol.engine.ServerToClientVillageExample

final case class FirstMorningPhase(filePath: String) extends ServerToClientVillageExample(filePath) {
  override val `type`: String = FirstMorningPhase.`type`
}

object FirstMorningPhase {
  val `type`: String = "village.server2client.FirstMorningPhase"
}
