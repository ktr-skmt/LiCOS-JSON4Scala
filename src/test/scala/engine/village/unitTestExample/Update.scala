package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class Update(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Update.`type`
}

object Update {
  val `type`: String = "unitTest/Update"
}
