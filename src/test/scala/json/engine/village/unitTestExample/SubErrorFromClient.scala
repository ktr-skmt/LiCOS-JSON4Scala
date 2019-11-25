package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class SubErrorFromClient(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubErrorFromClient.`type`
}

object SubErrorFromClient {
  val `type`: String = "unitTest/SubErrorFromClient"
}
