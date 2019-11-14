package licos.json.validation.village

import licos.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

object ChatSettingsValidation {
  private val label: String        = "chatSettings"
  val `@context`:    Reads[String] = pattern(WerewolfWorld.context(label).r)
  val `@id`:         Reads[String] = pattern(LiCOSOnline.stateRegex(label).r)
}
