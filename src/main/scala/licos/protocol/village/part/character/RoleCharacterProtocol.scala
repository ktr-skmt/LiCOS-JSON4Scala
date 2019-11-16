package licos.protocol.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonRoleCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Role}
import licos.protocol.village.part.role.SimpleRoleProtocol
import licos.util.LiCOSOnline

final case class RoleCharacterProtocol(character: Character, villageId: Long, lang: Locale, role: Role) {

  val json: JsonRoleCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, "/myCharacter")
    JsonRoleCharacter(
      CharacterContext.iri,
      `@id`,
      character.intId,
      character.name.json(Option(lang)),
      character.icon,
      SimpleRoleProtocol(role, villageId, lang).json(`@id`)
    )
  }

}
