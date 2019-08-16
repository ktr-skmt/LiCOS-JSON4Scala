package engine.village.example.client2server

import engine.ClientToServerVillageExample

case class Vote(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Vote.`type`
}

object Vote {
  val `type`: String = "Vote"
}