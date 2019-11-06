package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class SubStar(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubStar.`type`
}

object SubStar {
  val `type`: String = "unitTest/SubStar"
}
