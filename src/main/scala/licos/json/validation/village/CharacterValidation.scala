package licos.json.validation.village

import licos.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax._

object CharacterValidation {
  private val label:   String = "character"
  val idStringPattern: String = "(?:1-9|1[0-5])"

  val `@context`: Reads[String] = pattern(WerewolfWorld.context(label).r)

  /** 5 patterns:
    * https://licos.online/state/0.3/village#3/myCharacter
    * https://licos.online/state/0.3/village#3/character
    * https://licos.online/state/0.3/village#3/role#villager/character#1
    * https://licos.online/state/0.3/village#3/role#villager/board#1/character#1
    * https://licos.online/state/0.3/village#3/extensionalDisclosureRange/character#2
    */
  val `@id`: Reads[String] = pattern(
    LiCOSOnline
      .state(
        s"""(?:myCharacter|character|(?:(?:role#${RoleValidation.roleNamesInLowerCase}(?:/board#$idStringPattern)?|extensionalDisclosureRange)/)character#$idStringPattern)"""
      )
      .r
  )
  val gender: Reads[String] = pattern("""(?:fe)?male""".r)
  val image:  Reads[String] = pattern(WerewolfWorld.characterImage("[a-z]").r)
  val id:     Reads[Int]    = min(1) keepAnd max(15)
  val status: Reads[String] = pattern("""(?:alive|dead|death by (?:execution|attack|fear)|unnatural death)""".r)
  val result: Reads[String] = pattern("""(?:win|lose)""".r)
}
