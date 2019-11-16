package licos.protocol.village.part.role

import java.util.Locale

import licos.json.element.village.iri.RoleContext
import licos.json.element.village.role.JsonRole
import licos.knowledge.Role
import licos.protocol.village.part.BoardResultProtocol
import licos.util.LiCOSOnline

final case class RoleProtocol(role:               Role,
                              isMine:             Boolean,
                              numberOfCharacters: Int,
                              board:              Seq[BoardResultProtocol],
                              villageId:          Long,
                              lang:               Locale) {

  val json: JsonRole = {
    val `@id`: String = LiCOSOnline.state(villageId, s"role#${role.name.en.toLowerCase}")
    JsonRole(
      RoleContext.iri,
      `@id`,
      role.name.json(Option(lang)),
      role.icon,
      isMine,
      numberOfCharacters,
      board.map(_.json(`@id`))
    )
  }

}
