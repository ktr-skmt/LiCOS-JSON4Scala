package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class VotingResultSummary(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = VotingResultSummary.`type`
}

object VotingResultSummary {
  val `type`: String = "unitTest/VotingResultSummary"
}
