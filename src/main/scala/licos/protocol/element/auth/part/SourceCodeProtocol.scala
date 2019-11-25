package licos.protocol.element.auth.part

import licos.json.element.auth.robot2server
import licos.json.element.auth.robot2server.{JsonProgrammingLanguage, JsonSourceCode}

import scala.collection.mutable.ListBuffer

final case class SourceCodeProtocol(
    timestamp:           String,
    programmingLanguage: Seq[ProgrammingLanguageProtocol],
    url:                 String
) {
  val json: Option[JsonSourceCode] = {

    val buffer = ListBuffer.empty[JsonProgrammingLanguage]
    programmingLanguage foreach { pl: ProgrammingLanguageProtocol =>
      pl.json foreach { jsonProgrammingLanguage: JsonProgrammingLanguage =>
        buffer += jsonProgrammingLanguage
      }
    }

    Some(
      robot2server.JsonSourceCode(
        timestamp,
        buffer.result,
        url
      )
    )
  }
}

object SourceCodeProtocol {

  def read(json: JsonSourceCode): Option[SourceCodeProtocol] = {

    val buffer = ListBuffer.empty[ProgrammingLanguageProtocol]
    json.programmingLanguage foreach { pl: JsonProgrammingLanguage =>
      ProgrammingLanguageProtocol.read(pl) foreach { plp: ProgrammingLanguageProtocol =>
        buffer += plp
      }
    }
    Some(
      SourceCodeProtocol(
        json.timestamp,
        buffer.result,
        json.url
      )
    )
  }

}
