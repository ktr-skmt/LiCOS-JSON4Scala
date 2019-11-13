package licos.json.validation.lobby

import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, maxLength, min, pattern}
import play.api.libs.functional.syntax._

object BuildVillageValidation {
  val id:      Reads[Long]   = min(-1L) keepAnd max(Long.MaxValue)
  val avatar:  Reads[String] = pattern("""(?:fixed|random)""".r)
  val comment: Reads[String] = maxLength[String](100)
}
