package licos.protocol.village.part

import java.util.Locale

import licos.json.element.village.JsonBoardResult
import licos.json.element.village.iri.BoardResultContext
import licos.knowledge.{Character, Phase, PolarityMark, Role}
import licos.protocol.village.part.character.SimpleCharacterProtocol

final case class BoardResultProtocol(character: Character,
                                     polarity:  PolarityMark,
                                     phase:     Phase,
                                     day:       Int,
                                     villageId: Long,
                                     locale:    Locale,
                                     role:      Role) {

  def json(`@id`: String): JsonBoardResult = {
    JsonBoardResult(
      BoardResultContext.iri,
      `@id`.concat(s"/board#${character.intId}"),
      SimpleCharacterProtocol(character, villageId, locale, role).json(`@id`),
      polarity.label,
      phase.label,
      day
    )
  }

}
