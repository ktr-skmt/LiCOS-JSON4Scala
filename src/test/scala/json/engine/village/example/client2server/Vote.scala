package json.engine.village.example.client2server

import json.engine.ClientToServerVillageExample

final case class Vote(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Vote.`type`
}

object Vote {
  val `type`: String = "Vote"
}
