package licos.protocol.village.part

import licos.json.element.village.JsonVotingResultDetail
import licos.protocol.village.part.character.SimpleCharacterProtocol
import licos.util.LiCOSOnline

final case class VotingResultDetailProtocol(sourceCharacter: SimpleCharacterProtocol,
                                            targetCharacter: SimpleCharacterProtocol,
                                            villageId:       Long) {

  val json: JsonVotingResultDetail = {
    val `@id`: String = LiCOSOnline
      .state(villageId, s"votingResultsDetails#${sourceCharacter.character.intId}-${targetCharacter.character.intId}")
    JsonVotingResultDetail(
      `@id`,
      sourceCharacter.json(`@id`),
      targetCharacter.json(`@id`)
    )
  }

}
