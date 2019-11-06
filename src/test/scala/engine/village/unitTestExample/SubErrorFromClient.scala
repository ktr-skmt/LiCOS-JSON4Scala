package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubErrorFromClient(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubErrorFromClient.`type`
}

object SubErrorFromClient {
  val `type`: String = "unitTest/SubErrorFromClient"
}
