package json.engine.auth.unitTestExample

import json.engine.AuthUnitTestExample

final case class ProgrammingLanguage(filePath: String) extends AuthUnitTestExample(filePath) {
  override val `type`: String = ProgrammingLanguage.`type`
}

object ProgrammingLanguage {
  val `type`: String = "unitTest/ProgrammingLanguage"
}
