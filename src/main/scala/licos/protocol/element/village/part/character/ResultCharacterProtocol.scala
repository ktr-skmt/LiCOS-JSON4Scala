package licos.protocol.element.village.part.character

import java.net.URL
import java.util.{Locale, UUID}

import licos.json.element.village.character.JsonResultCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Outcome, Role, Status}
import licos.protocol.element.village.part.AvatarProtocol
import licos.protocol.element.village.part.role.SimpleRoleProtocol
import licos.util.LiCOSOnline

final case class ResultCharacterProtocol(
    character:   Character,
    isMine:      Boolean,
    role:        Role,
    status:      Status,
    result:      Outcome,
    token:       UUID,
    avatarName:  String,
    avatarImage: URL,
    villageId:   Long,
    language:    Locale
) {

  lazy val json: JsonResultCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, "")
    JsonResultCharacter(
      CharacterContext.iri,
      `@id`,
      character.getId,
      character.name.json(Option(language)),
      character.icon,
      isMine,
      SimpleRoleProtocol(role, villageId, language).json(`@id`),
      status.label,
      result.label,
      AvatarProtocol(token, avatarName, avatarImage).json(`@id`)
    )
  }

}
