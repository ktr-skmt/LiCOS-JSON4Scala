package json.engine.auth.unitTestExample

import json.engine.AuthUnitTestExample

final case class SourceCode(filePath: String) extends AuthUnitTestExample(filePath) {
  override val `type`: String = SourceCode.`type`
}

object SourceCode {
  val `type`: String = "unitTest/SourceCode"
}
