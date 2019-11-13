package licos.json.validation.village

import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

object ErrorValidation {

  val severity: Reads[String] = pattern("""(?:error|warning)""".r)
  val source:   Reads[String] = pattern(""".{0,16384}""".r)

}
