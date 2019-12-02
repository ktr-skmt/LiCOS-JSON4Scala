package licos.json.validation.village

import licos.util.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

object BoardResultValidation {
  private val label: String        = "boardResult"
  val `@context`:    Reads[String] = pattern(WerewolfWorld.context(label).r)
  val `@id`: Reads[String] = pattern(
    LiCOSOnline
      .stateRegex(s"""role#${RoleValidation.roleNamesInLowerCase}/board#${CharacterValidation.idStringPattern}""")
      .r
  )
  val polarity: Reads[String] = pattern("""(?:positive|negative|[?ΔOX])""".r)
}
