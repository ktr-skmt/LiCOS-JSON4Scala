package licos.json.validation.village

import licos.LiCOSOnline
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax._

object VotingResultValidation {
  object votingResultsSummary {
    object item {
      val `@id`: Reads[String] = pattern(
        LiCOSOnline.state(s"""votingResultsSummary#${CharacterValidation.idStringPattern}""").r
      )
      val numberOfVotes: Reads[Int] = min(0) keepAnd max(15)
      val rankOfVotes:   Reads[Int] = min(1) keepAnd max(15)
    }
  }

  object votingResultsDetails {
    object item {
      val `@id`: Reads[String] = pattern(
        LiCOSOnline
          .state(
            s"""votingResultsDetails#${CharacterValidation.idStringPattern}-${CharacterValidation.idStringPattern}"""
          )
          .r
      )
    }
  }
}
