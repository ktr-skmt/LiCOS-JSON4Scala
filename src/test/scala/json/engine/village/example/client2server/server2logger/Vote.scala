package json.engine.village.example.client2server.server2logger

import json.engine.ServerToLoggerVillageExample

final case class Vote(filePath: String) extends ServerToLoggerVillageExample(filePath) {
  override val `type`: String = Vote.`type`
}

object Vote {
  val `type`: String = "Vote"
}
