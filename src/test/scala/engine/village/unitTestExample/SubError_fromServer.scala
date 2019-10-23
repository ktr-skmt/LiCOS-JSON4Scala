package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubError_fromServer(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubError_fromServer.`type`
}

object SubError_fromServer {
  val `type`: String = "unitTest/SubError_fromServer"
}