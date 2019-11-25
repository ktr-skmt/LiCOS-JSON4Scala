package json.engine.village.unitTestExample

import json.engine.VillageUnitTestExample

final case class VotingResultDetail(filePath: String) extends VillageUnitTestExample(filePath) {
  override val `type`: String = VotingResultDetail.`type`
}

object VotingResultDetail {
  val `type`: String = "unitTest/VotingResultDetail"
}
