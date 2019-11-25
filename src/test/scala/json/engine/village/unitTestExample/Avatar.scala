package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class Avatar(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = Avatar.`type`
}

object Avatar {
  val `type`: String = "unitTest/Avatar"
}
