package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonError
import licos.knowledge.{Data2Knowledge, Severity}
import licos.protocol.element.village.part.NameProtocol

final case class ErrorFromClientProtocol(
    village:  Village,
    content:  NameProtocol,
    severity: Severity,
    source:   String
) extends Client2ServerVillageMessageProtocol {

  val json: Option[JsonError] = {
    server2logger.ErrorFromClientProtocol(village, content, severity, source, Nil).json
  }

}

object ErrorFromClientProtocol {

  def read(json: JsonError, village: Village): Option[ErrorFromClientProtocol] = {
    val content = Data2Knowledge.name(json.content)

    val severityOpt: Option[Severity] = Data2Knowledge.severityOpt(json.severity)

    if (severityOpt.nonEmpty) {
      Some(
        ErrorFromClientProtocol(
          village,
          content,
          severityOpt.get,
          json.source
        )
      )
    } else {
      None
    }
  }

}
