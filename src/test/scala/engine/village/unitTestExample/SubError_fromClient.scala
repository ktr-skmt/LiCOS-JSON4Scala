package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubError_fromClient(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubError_fromClient.`type`
}

object SubError_fromClient {
  val `type`: String = "unitTest/SubError_fromClient"
}