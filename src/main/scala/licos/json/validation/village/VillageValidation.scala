package licos.json.validation.village

import licos.util.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax._

object VillageValidation {
  private val label:        String        = "village"
  val `@context`:           Reads[String] = pattern(WerewolfWorld.context(label).r)
  val `@id`:                Reads[String] = pattern(LiCOSOnline.stateVillage.r)
  val id:                   Reads[Long]   = min(0L) keepAnd max(Long.MaxValue)
  val name:                 Reads[String] = pattern("[A-Za-z](?:[\u0021-\u007E]|\u0020[\u0021-\u007E]){4,29}".r)
  val totalNumberOfPlayers: Reads[Int]    = min(4) keepAnd max(15)
  val language:             Reads[String] = pattern("(?:ar|de|es|en|fr|it|ja|pt|ru|uk|vi|zh-CN|zh-TW)".r)
}
