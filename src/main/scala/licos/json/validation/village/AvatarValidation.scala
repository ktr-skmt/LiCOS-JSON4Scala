package licos.json.validation.village

import licos.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

object AvatarValidation {
  private val label: String        = "avatar"
  val `@context`:    Reads[String] = pattern(WerewolfWorld.context(label).r)
  val `@id`:         Reads[String] = pattern(LiCOSOnline.state(label).r)
  val token: Reads[String] = pattern(
    """[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}""".r
  )
  val name:  Reads[String] = pattern("""[A-Za-z][0-9A-Za-z]{4,14}""".r)
  val image: Reads[String] = pattern(WerewolfWorld.characterImage("""[a-z]""").r)
}
