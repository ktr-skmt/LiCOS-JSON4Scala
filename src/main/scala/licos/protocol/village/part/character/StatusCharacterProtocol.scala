package licos.protocol.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Role, Status}
import licos.protocol.village.part.role.SimpleRoleProtocol
import licos.util.LiCOSOnline

final case class StatusCharacterProtocol(character:     Character,
                                         villageId:     Long,
                                         lang:          Locale,
                                         role:          Role,
                                         status:        Status,
                                         isHumanPlayer: Boolean) {

  val json: JsonStatusCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, s"character#${character.intId}")
    JsonStatusCharacter(
      CharacterContext.iri,
      `@id`,
      character.intId,
      character.name.json(Option(lang)),
      character.icon,
      SimpleRoleProtocol(role, villageId, lang).json(`@id`),
      status.label,
      isHumanPlayer
    )
  }

}
