package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubErrorFromServer(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubErrorFromServer.`type`
}

object SubErrorFromServer {
  val `type`: String = "unitTest/SubErrorFromServer"
}