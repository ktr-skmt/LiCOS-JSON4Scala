package licos.json.validation.village

import licos.util.LiCOSOnline
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax._

object TimeValidation {
  val `@id`: Reads[String] = pattern(
    LiCOSOnline.stateRegex(s"""character#${CharacterValidation.idStringPattern}/update""").r
  )
  val phase: Reads[String] = pattern("""(?:flavor text|morning|noon|night|result|post-mortem discussion)""".r)
  val day:   Reads[Int]    = min(-1) keepAnd max(13)
}
