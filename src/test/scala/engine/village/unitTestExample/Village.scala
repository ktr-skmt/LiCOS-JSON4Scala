package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class Village(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Village.`type`
}

object Village {
  val `type`: String = "unitTest/Village"
}