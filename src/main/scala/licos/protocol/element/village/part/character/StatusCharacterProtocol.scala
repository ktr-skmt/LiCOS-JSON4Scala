package licos.protocol.element.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Role, Status}
import licos.protocol.element.village.part.role.SimpleRoleProtocol
import licos.util.LiCOSOnline

final case class StatusCharacterProtocol(
    character:     Character,
    role:          Role,
    status:        Status,
    isHumanPlayer: Boolean,
    villageId:     Long,
    language:      Locale
) {

  val json: JsonStatusCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, s"character#${character.getId}")
    JsonStatusCharacter(
      CharacterContext.iri,
      `@id`,
      character.getId,
      character.name.json(Option(language)),
      character.icon,
      SimpleRoleProtocol(role, villageId, language).json(`@id`),
      status.label,
      isHumanPlayer
    )
  }

}
