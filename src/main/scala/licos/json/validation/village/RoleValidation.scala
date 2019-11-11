package licos.json.validation.village

import licos.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax._

object RoleValidation {
  private val label:        String        = "role"
  private val roleNames:    String        = """(?:Hunter|Madman|Mason|Medium|Seer|Villager|Werehamster|Werewolf)"""
  def roleNamesInLowerCase: String        = roleNames.toLowerCase
  val `@context`:           Reads[String] = pattern(WerewolfWorld.context(label).r)

  /** 4 patterns:
    * https://licos.online/state/0.3/village#3/role
    * https://licos.online/state/0.3/village#3/character#1/role
    * https://licos.online/state/0.3/village#3/role#villager
    * https://licos.online/state/0.3/village#3/extensionalDisclosureRange/character#10/role
    */
  val `@id`: Reads[String] = pattern(
    LiCOSOnline
      .state(
        s"""(?:(?:(?:extensionalDisclosureRange/)?character#(?:[1-9]|1[0-5])/)?role|role(?:#$roleNamesInLowerCase))"""
      )
      .r
  )
  object name {
    val en: Reads[String] = pattern(roleNames.r)
    val ja: Reads[String] = pattern("""(?:狩人|狂人|共有者|霊媒師|占い師|村人|ハムスター人間|人狼)""".r)
  }
  val image:              Reads[String] = pattern(WerewolfWorld.roleImage(roleNamesInLowerCase).r)
  val numberOfCharacters: Reads[Int]    = min(0) keepAnd max(7)
}
