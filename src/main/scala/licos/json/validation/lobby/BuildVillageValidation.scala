package licos.json.validation.lobby

import play.api.libs.json.Reads
import play.api.libs.json.Reads.{maxLength, pattern}

object BuildVillageValidation {
  val avatar:  Reads[String] = pattern("""(?:fixed|random)""".r)
  val comment: Reads[String] = maxLength[String](100)
}
