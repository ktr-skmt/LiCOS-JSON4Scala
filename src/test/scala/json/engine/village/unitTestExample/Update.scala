package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class Update(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Update.`type`
}

object Update {
  val `type`: String = "unitTest/Update"
}
