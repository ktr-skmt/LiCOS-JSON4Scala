package licos.json.validation.village

import licos.util.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax._

import scala.util.matching.Regex

object RoleValidation {
  private val label:        String        = "role"
  private val roleNames:    String        = """(?:Hunter|Madman|Mason|Master|Medium|Seer|Villager|Werehamster|Werewolf)"""
  def roleNamesInLowerCase: String        = roleNames.toLowerCase
  val `@context`:           Reads[String] = pattern(WerewolfWorld.context(label).r)

  val `@idRegex`: Regex = LiCOSOnline
    .stateRegex(
      s"""(?:(?:(?:extensionalDisclosureRange/)?character#${CharacterValidation.idStringPattern}/)?role|role(?:#$roleNamesInLowerCase))"""
    )
    .r

  val `@id`: Reads[String] = pattern(`@idRegex`)
  object name {
    val en: Reads[String] = pattern(roleNames.r)
    val ja: Reads[String] = pattern("""(?:狩人|狂人|共有者|霊媒師|占い師|村人|ハムスター人間|人狼)""".r)
  }
  val image:           Reads[String] = pattern(WerewolfWorld.roleIcon(roleNamesInLowerCase).r)
  val numberOfPlayers: Reads[Int]    = min(0) keepAnd max(7)

}
