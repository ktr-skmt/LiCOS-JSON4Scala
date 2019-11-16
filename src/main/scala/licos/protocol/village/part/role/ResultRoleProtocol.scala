package licos.protocol.village.part.role

import java.util.Locale

import licos.json.element.village.iri.RoleContext
import licos.json.element.village.role.JsonResultRole
import licos.knowledge.Role
import licos.protocol.village.part.character.SimpleCharacterProtocol
import licos.util.LiCOSOnline

final case class ResultRoleProtocol(role:      Role,
                                    isMine:    Boolean,
                                    character: Seq[SimpleCharacterProtocol],
                                    villageId: Long,
                                    locale:    Locale) {

  def json: JsonResultRole = {
    val `@id`: String = LiCOSOnline.state(villageId, s"role#${role.name.en.toLowerCase}")
    JsonResultRole(
      RoleContext.iri,
      `@id`,
      isMine,
      role.name.json(Option(locale)),
      role.icon,
      role.numberOfPlayers,
      character.map(_.json(`@id`))
    )
  }

}
