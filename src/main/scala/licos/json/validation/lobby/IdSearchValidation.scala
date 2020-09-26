package licos.json.validation.lobby

import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min}
import play.api.libs.functional.syntax._

object IdSearchValidation {
  val idForSearching: Reads[Long] = min(-1L) keepAnd max(Long.MaxValue)
}
