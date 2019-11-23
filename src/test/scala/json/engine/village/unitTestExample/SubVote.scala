package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class SubVote(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = SubVote.`type`
}

object SubVote {
  val `type`: String = "unitTest/SubVote"
}
