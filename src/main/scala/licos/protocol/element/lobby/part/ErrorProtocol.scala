package licos.protocol.element.lobby.part

import java.util.Locale

import licos.json.element.village.JsonSubError
import licos.knowledge.{Data2Knowledge, Severity}
import licos.protocol.element.village.part.NameProtocol

final case class ErrorProtocol(content: NameProtocol, severity: Severity, source: String, isFromServer: Boolean) {

  val json: Option[JsonSubError] = {
    Some(
      JsonSubError(
        content.json(Some(Locale.ENGLISH)),
        severity.label,
        source,
        isFromServer
      )
    )
  }

}

object ErrorProtocol {

  def read(json: JsonSubError): Option[ErrorProtocol] = {

    val content:  NameProtocol     = Data2Knowledge.name(json.content)
    val severity: Option[Severity] = Data2Knowledge.severityOpt(json.severity)

    if (severity.nonEmpty) {
      Some(
        ErrorProtocol(
          content,
          severity.get,
          json.source,
          json.isFromServer
        )
      )
    } else {
      None
    }
  }

}
