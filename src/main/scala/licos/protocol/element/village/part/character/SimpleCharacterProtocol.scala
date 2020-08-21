package licos.protocol.element.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.Character
import licos.util.LiCOSOnline

final case class SimpleCharacterProtocol(character: Character, villageId: Long, language: Locale) {
  def json(`@id`: String): JsonSimpleCharacter = {
    JsonSimpleCharacter(
      CharacterContext.iri,
      LiCOSOnline
        .state(villageId, `@id`.concat(s"/character#${character.getId.toString}")),
      character.getId,
      character.name.json(Option(language)),
      character.icon
    )
  }
}
