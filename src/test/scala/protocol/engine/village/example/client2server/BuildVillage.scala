package protocol.engine.village.example.client2server

import protocol.engine.ClientToServerVillageExample

final case class BuildVillage(filePath: String) extends ClientToServerVillageExample(filePath) {
  override val `type`: String = BuildVillage.`type`
}

object BuildVillage {
  val `type`: String = "village.client2server.BuildVillage"
}
