package licos.protocol.village.part

import java.util.Locale

import licos.json.element.village.JsonVotingResultSummary
import licos.knowledge.{Character, Role}
import licos.protocol.village.part.character.SimpleCharacterProtocol
import licos.util.LiCOSOnline

final case class VotingResultSummaryProtocol(
    character:     Character,
    numberOfVotes: Int,
    rankOfVotes:   Int,
    villageId:     Long,
    locale:        Locale,
    role:          Role
) {

  val json: JsonVotingResultSummary = {
    val `@id`: String = LiCOSOnline.state(villageId, s"votingResultsSummary#${character.intId}")
    JsonVotingResultSummary(
      `@id`,
      SimpleCharacterProtocol(
        character,
        villageId,
        locale,
        role
      ).json(`@id`),
      numberOfVotes,
      rankOfVotes
    )
  }

}
