package json.engine.village.example.server2client.server2logger

import json.engine.ServerToLoggerVillageExample

final case class Phase(filePath: String) extends ServerToLoggerVillageExample(filePath) {
  override val `type`: String = Phase.`type`
}

object Phase {
  val `type`: String = "Phase"
}
