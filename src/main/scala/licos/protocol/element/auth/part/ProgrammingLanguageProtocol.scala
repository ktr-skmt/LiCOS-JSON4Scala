package licos.protocol.element.auth.part

import licos.json.element.auth.robot2server.JsonProgrammingLanguage

final case class ProgrammingLanguageProtocol(name: String, version: String) {

  lazy val json: Option[JsonProgrammingLanguage] = {
    Some(
      JsonProgrammingLanguage(
        name,
        version
      )
    )
  }

}

object ProgrammingLanguageProtocol {

  def read(json: JsonProgrammingLanguage): Option[ProgrammingLanguageProtocol] = {
    Some(
      ProgrammingLanguageProtocol(
        json.name,
        json.version
      )
    )
  }

}
