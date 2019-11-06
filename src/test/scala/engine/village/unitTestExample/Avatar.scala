package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class Avatar(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Avatar.`type`
}

object Avatar {
  val `type`: String = "unitTest/Avatar"
}
