package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonError
import licos.knowledge.{Data2Knowledge, Severity}
import licos.protocol.element.village.part.NameProtocol
import play.api.libs.json.{JsValue, Json}

final case class ErrorFromServerProtocol(
    village:  Village,
    content:  NameProtocol,
    severity: Severity,
    source:   String
) extends Server2ClientVillageMessageProtocol {

  private val json: Option[JsonError] = {
    server2logger.ErrorFromServerProtocol(village, content, severity, source, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonError =>
      Json.toJson(j)
    }
  }

}

object ErrorFromServerProtocol {

  def read(json: JsonError, village: Village): Option[ErrorFromServerProtocol] = {
    if (json.isFromServer) {
      val content:     NameProtocol     = Data2Knowledge.name(json.content)
      val severityOpt: Option[Severity] = Data2Knowledge.severityOpt(json.severity)

      if (severityOpt.nonEmpty) {
        Some(
          ErrorFromServerProtocol(
            village,
            content,
            severityOpt.get,
            json.source
          )
        )
      } else {
        None
      }
    } else {
      None
    }
  }

}
