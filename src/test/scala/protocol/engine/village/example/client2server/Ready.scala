package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class Ready(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = Ready.`type`
}

object Ready {
  val `type`: String = "village.client2server.Ready"
}
