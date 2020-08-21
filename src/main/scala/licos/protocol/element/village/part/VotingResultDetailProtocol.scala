package licos.protocol.element.village.part

import licos.json.element.village.JsonVotingResultDetail
import licos.protocol.element.village.part.character.SimpleCharacterProtocol
import licos.util.LiCOSOnline

final case class VotingResultDetailProtocol(
    sourceCharacter: SimpleCharacterProtocol,
    targetCharacter: SimpleCharacterProtocol,
    villageId:       Long
) {

  lazy val json: JsonVotingResultDetail = {
    val `@id`: String = LiCOSOnline
      .state(
        villageId,
        s"votingResultsDetails#${sourceCharacter.character.getId.toString}-${targetCharacter.character.getId.toString}"
      )
    JsonVotingResultDetail(
      `@id`,
      sourceCharacter.json(`@id`),
      targetCharacter.json(`@id`)
    )
  }

}
