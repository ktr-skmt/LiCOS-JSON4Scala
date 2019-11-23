package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class SubPhase(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubPhase.`type`
}

object SubPhase {
  val `type`: String = "unitTest/SubPhase"
}
