package licos.json.validation.village

import licos.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax._

object VillageValidation {
  private val label:           String        = "village"
  val `@context`:              Reads[String] = pattern(WerewolfWorld.context(label).r)
  val `@id`:                   Reads[String] = pattern(LiCOSOnline.stateVillage.r)
  val id:                      Reads[Long]   = min(0L)
  val name:                    Reads[String] = pattern("""[A-Za-z][!-~]{4,29}""".r)
  val totalNumberOfCharacters: Reads[Int]    = min(4) keepAnd max(15)
  val lang:                    Reads[String] = pattern("""(?:ar|de|es|en|fr|it|ja|pt|ru|uk|vi|zh-CN|zh-TW)""".r)
}
