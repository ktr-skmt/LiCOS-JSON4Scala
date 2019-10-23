package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubFlavorText(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubFlavorText.`type`
}

object SubFlavorText {
  val `type`: String = "unitTest/SubFlavorText"
}