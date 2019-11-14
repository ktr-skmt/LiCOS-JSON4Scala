package licos.json.validation.village

import licos.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

import scala.util.matching.Regex

object AvatarValidation {
  private val label: String        = "avatar"
  val `@context`:    Reads[String] = pattern(WerewolfWorld.context(label).r)
  val `@idRegex`:    Regex         = LiCOSOnline.stateRegex(s"""(?:character#${CharacterValidation.idStringPattern}/)?$label""").r
  val `@id`:         Reads[String] = pattern(`@idRegex`)
  val token: Reads[String] = pattern(
    """[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}""".r
  )
  val name:  Reads[String] = pattern("""[A-Za-z](?:[\u0021-\u007E]|\u0020[\u0021-\u007E]){4,14}""".r)
  val image: Reads[String] = pattern(WerewolfWorld.characterIcon("""(?:[a-z]|anonymous)""").r)
}
