package licos.protocol.element.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Role, Status}
import licos.protocol.element.village.part.role.SimpleRoleProtocol
import licos.util.LiCOSOnline

final case class StatusCharacterProtocol(
    character:     Character,
    villageId:     Long,
    language:          Locale,
    role:          Role,
    status:        Status,
    isHumanPlayer: Boolean
) {

  val json: JsonStatusCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, s"character#${character.intId}")
    JsonStatusCharacter(
      CharacterContext.iri,
      `@id`,
      character.intId,
      character.name.json(Option(language)),
      character.icon,
      SimpleRoleProtocol(role, villageId, language).json(`@id`),
      status.label,
      isHumanPlayer
    )
  }

}
