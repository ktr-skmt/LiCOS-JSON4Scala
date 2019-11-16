package licos.protocol.village.part.character

import java.util.{Locale, UUID}

import licos.json.element.village.character.JsonResultCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Outcome, Role, Status}
import licos.protocol.village.part.AvatarProtocol
import licos.protocol.village.part.role.SimpleRoleProtocol
import licos.util.LiCOSOnline

final case class ResultCharacterProtocol(character:   Character,
                                         villageId:   Long,
                                         lang:        Locale,
                                         isMine:      Boolean,
                                         role:        Role,
                                         status:      Status,
                                         result:      Outcome,
                                         token:       UUID,
                                         avatarName:  String,
                                         avatarImage: String) {

  val json: JsonResultCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, "")
    JsonResultCharacter(
      CharacterContext.iri,
      `@id`,
      character.intId,
      character.name.json(Option(lang)),
      character.icon,
      isMine,
      SimpleRoleProtocol(role, villageId, lang).json(`@id`),
      status.label,
      result.label,
      AvatarProtocol(token, avatarName, avatarImage).json(`@id`)
    )
  }

}
