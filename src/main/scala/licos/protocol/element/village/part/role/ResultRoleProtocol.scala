package licos.protocol.element.village.part.role

import java.util.Locale

import licos.json.element.village.iri.RoleContext
import licos.json.element.village.role.JsonResultRole
import licos.knowledge.Role
import licos.protocol.element.village.part.character.SimpleCharacterProtocol
import licos.util.LiCOSOnline

final case class ResultRoleProtocol(
    role:      Role,
    isMine:    Boolean,
    character: Seq[SimpleCharacterProtocol],
    villageId: Long,
    language:  Locale
) {

  lazy val json: JsonResultRole = {
    val `@id`: String = LiCOSOnline.state(villageId, s"role#${role.name.en.toLowerCase(Locale.ENGLISH)}")
    JsonResultRole(
      RoleContext.iri,
      `@id`,
      isMine,
      role.name.json(Option(language)),
      role.icon,
      role.numberOfPlayers,
      character.map(_.json(`@id`))
    )
  }

}
