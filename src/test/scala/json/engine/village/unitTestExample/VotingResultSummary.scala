package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

case class VotingResultSummary(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = VotingResultSummary.`type`
}

object VotingResultSummary {
  val `type`: String = "unitTest/VotingResultSummary"
}
