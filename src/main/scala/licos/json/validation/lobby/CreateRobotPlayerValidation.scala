package licos.json.validation.lobby

import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

object CreateRobotPlayerValidation {
  val automationType: Reads[String] = pattern("""(?:fully automated|semi-automated)""".r)
}
