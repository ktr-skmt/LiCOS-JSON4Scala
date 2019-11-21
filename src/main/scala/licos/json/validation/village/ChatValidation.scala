package licos.json.validation.village

import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, maxLength, min, minLength}
import play.api.libs.functional.syntax._

object ChatValidation {
  val id:                      Reads[Int] = min(1) keepAnd max(Int.MaxValue)
  val counter:                 Reads[Int] = min(0) keepAnd max(Int.MaxValue)
  val maxNumberOfChatMessages: Reads[Int] = min(1) keepAnd max(Int.MaxValue)
  val interval:                Reads[Int] = min(1) keepAnd max(Int.MaxValue)
  object text {
    val `@value`:    Reads[String] = minLength[String](1) keepAnd maxLength[String](140)
    val `@language`: Reads[String] = VillageValidation.language
  }
  val maxLengthOfUnicodeCodePoints: Reads[Int] = min(140) keepAnd max(140)
}
