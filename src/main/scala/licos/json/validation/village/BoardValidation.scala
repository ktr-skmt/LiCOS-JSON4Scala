package licos.json.validation.village

import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

object BoardValidation {
  val prediction: Reads[String] = pattern("""[?ΔOX]""".r)
}
