package licos.json.validation.lobby

import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

object PingValidation {
  object results {
    val ping:   Reads[String] = pattern("""[0-9]{2}.[0-9]{3}""".r)
    val status: Reads[String] = pattern("""(?:safe|warning|danger)""".r)
  }
}
