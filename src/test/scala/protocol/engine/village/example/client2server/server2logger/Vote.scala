package protocol.engine.village.example.client2server.server2logger

import protocol.engine.ServerToLoggerVillageExample

final case class Vote(filePath: String) extends ServerToLoggerVillageExample(filePath) {
  override val `type`: String = Vote.`type`
}

object Vote {
  val `type`: String = "village.client2server.server2logger.Vote"
}
