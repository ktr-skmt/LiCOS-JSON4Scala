package licos.protocol.village.part

import licos.json.element.village.JsonUpdate
import licos.knowledge.Phase

final case class UpdateProtocol(phase: Phase, day: Int) {
  def json(`@id`: String): JsonUpdate = JsonUpdate(
    `@id`.concat("/update"),
    phase.label,
    day
  )
}
