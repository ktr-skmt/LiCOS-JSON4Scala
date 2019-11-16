package licos.protocol.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Role}
import licos.util.LiCOSOnline

final case class SimpleCharacterProtocol(character: Character, villageId: Long, lang: Locale, role: Role) {
  def json(`@id`: String): JsonSimpleCharacter = {
    JsonSimpleCharacter(
      CharacterContext.iri,
      LiCOSOnline
        .state(villageId, `@id`.concat(s"/character#${character.intId}")),
      character.intId,
      character.name.json(Option(lang)),
      character.icon
    )
  }
}
