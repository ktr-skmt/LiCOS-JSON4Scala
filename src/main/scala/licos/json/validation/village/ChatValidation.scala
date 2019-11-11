package licos.json.validation.village

import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, maxLength, min, minLength, pattern}
import play.api.libs.functional.syntax._

object ChatValidation {
  val id:                      Reads[Int]    = min(1)
  val counter:                 Reads[Int]    = min(0)
  val maxNumberOfChatMessages: Reads[Int]    = min(1)
  val interval:                Reads[String] = pattern("""[0-9]+s""".r)
  object text {
    val `@value`:    Reads[String] = minLength[String](1) keepAnd maxLength[String](140)
    val `@language`: Reads[String] = VillageValidation.lang
  }
  val maxLengthOfUnicodeCodePoints: Reads[Int] = min(140) keepAnd max(140)
}
