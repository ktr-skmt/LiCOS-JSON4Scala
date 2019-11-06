package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class BoardPolarity(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = BoardPolarity.`type`
}

object BoardPolarity {
  val `type`: String = "unitTest/BoardPolarity"
}
