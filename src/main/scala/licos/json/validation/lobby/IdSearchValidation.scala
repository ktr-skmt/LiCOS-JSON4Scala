package licos.json.validation.lobby

import play.api.libs.json.Reads
import play.api.libs.json.Reads.min

object IdSearchValidation {
  val idForSearching: Reads[Int] = min(-1)
}
