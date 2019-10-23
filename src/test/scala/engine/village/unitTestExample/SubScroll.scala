package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubScroll(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubScroll.`type`
}

object SubScroll {
  val `type`: String = "unitTest/SubScroll"
}