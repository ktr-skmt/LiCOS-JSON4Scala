package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonError
import licos.knowledge.{ErrorSeverity, FatalSeverity, InfoSeverity, Severity, WarningSeverity}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.NameProtocol

final case class ErrorFromServerProtocol(
    village:  Village,
    content:  NameProtocol,
    severity: Severity,
    source:   String
) extends VillageMessageProtocol {

  val json: Option[JsonError] = {
    server2logger.ErrorFromServerProtocol(village, content, severity, source, Nil).json
  }

}

object ErrorFromServerProtocol {

  def read(json: JsonError, village: Village): Option[ErrorFromServerProtocol] = {
    if (json.isFromServer) {
      val content = NameProtocol().en(json.content.en)
      json.content.ar.foreach(content.ar)
      json.content.de.foreach(content.de)
      json.content.es.foreach(content.es)
      json.content.fr.foreach(content.fr)
      json.content.it.foreach(content.it)
      json.content.ja.foreach(content.ja)
      json.content.pt.foreach(content.pt)
      json.content.ru.foreach(content.ru)
      json.content.uk.foreach(content.uk)
      json.content.vi.foreach(content.vi)
      json.content.zhCN.foreach(content.zhCN)
      json.content.zhTW.foreach(content.zhTW)

      val severityOpt: Option[Severity] = {
        json.severity match {
          case FatalSeverity.label => Some(FatalSeverity)
          case ErrorSeverity.label => Some(ErrorSeverity)
          case WarningSeverity.label => Some(WarningSeverity)
          case InfoSeverity.label => Some(InfoSeverity)
          case _ => None
        }
      }

      if (severityOpt.nonEmpty) {
        Some(ErrorFromServerProtocol(
          village,
          content,
          severityOpt.get,
          json.source
        ))
      } else {
        None
      }
    } else {
      None
    }
  }

}
