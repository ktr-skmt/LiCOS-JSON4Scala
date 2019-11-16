package licos.protocol.village.client2server

import licos.json.element.village.JsonError
import licos.knowledge.Severity
import licos.protocol.village.part.NameProtocol
import licos.state.VillageState

final case class ErrorFromClientProtocol(state:    VillageState,
                                         content:  NameProtocol,
                                         severity: Severity,
                                         source:   String) {

  val json: Option[JsonError] = {
    server2logger.ErrorFromClientProtocol(state, content, severity, source, Nil).json
  }

}
