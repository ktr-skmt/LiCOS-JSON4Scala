package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class SubScroll(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubScroll.`type`
}

object SubScroll {
  val `type`: String = "unitTest/SubScroll"
}
