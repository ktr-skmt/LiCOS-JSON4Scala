package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class SubStar(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubStar.`type`
}

object SubStar {
  val `type`: String = "unitTest/SubStar"
}
