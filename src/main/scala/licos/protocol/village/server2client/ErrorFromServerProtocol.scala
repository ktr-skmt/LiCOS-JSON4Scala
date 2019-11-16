package licos.protocol.village.server2client

import licos.json.element.village.JsonError
import licos.knowledge.Severity
import licos.protocol.village.part.NameProtocol
import licos.state.VillageState

final case class ErrorFromServerProtocol(state:    VillageState,
                                         content:  NameProtocol,
                                         severity: Severity,
                                         source:   String) {

  val json: Option[JsonError] = {
    server2logger.ErrorFromServerProtocol(state, content, severity, source, Nil).json
  }

}
