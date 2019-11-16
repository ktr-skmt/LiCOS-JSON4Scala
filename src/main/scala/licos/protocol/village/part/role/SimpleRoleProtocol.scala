package licos.protocol.village.part.role

import java.util.Locale

import licos.json.element.village.iri.RoleContext
import licos.json.element.village.role.JsonSimpleRole
import licos.knowledge.Role

final case class SimpleRoleProtocol(role: Role, villageId: Long, lang: Locale) {

  def json(`@id`: String): JsonSimpleRole = JsonSimpleRole(
    RoleContext.iri,
    `@id`.concat("/role"),
    role.name.json(Option(lang)),
    role.icon
  )

}
