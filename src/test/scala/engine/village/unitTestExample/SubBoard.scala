package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubBoard(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubBoard.`type`
}

object SubBoard {
  val `type`: String = "unitTest/SubBoard"
}