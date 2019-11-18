package licos.protocol.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonError
import licos.knowledge.Severity
import licos.protocol.village.part.NameProtocol

final case class ErrorFromClientProtocol(
    village:  Village,
    content:  NameProtocol,
    severity: Severity,
    source:   String
) {

  val json: Option[JsonError] = {
    server2logger.ErrorFromClientProtocol(village, content, severity, source, Nil).json
  }

}
