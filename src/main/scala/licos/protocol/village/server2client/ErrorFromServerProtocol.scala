package licos.protocol.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonError
import licos.knowledge.Severity
import licos.protocol.village.part.NameProtocol

final case class ErrorFromServerProtocol(
    village:  Village,
    content:  NameProtocol,
    severity: Severity,
    source:   String
) {

  val json: Option[JsonError] = {
    server2logger.ErrorFromServerProtocol(village, content, severity, source, Nil).json
  }

}
