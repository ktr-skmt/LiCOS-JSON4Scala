package engine.village.unitTestExample

import engine.VillageUnitTestExample

case class VotingResultDetail(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = VotingResultDetail.`type`
}

object VotingResultDetail {
  val `type`: String = "unitTest/VotingResultDetail"
}
