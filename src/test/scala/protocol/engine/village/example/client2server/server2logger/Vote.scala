package protocol.engine.village.example.client2server.server2logger

import protocol.engine.ClientToServerVillageExample

final case class Vote(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Vote.`type`
}

object Vote {
  val `type`: String = "village.client2server.server2logger.Vote"
}
