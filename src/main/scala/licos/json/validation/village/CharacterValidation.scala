package licos.json.validation.village

import licos.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax._

import scala.util.matching.Regex

object CharacterValidation {
  private val label:   String = "character"
  val idStringPattern: String = "(?:[1-9]|1[0-5])"

  val `@context`: Reads[String] = pattern(WerewolfWorld.context(label).r)

  val `@idRegex`: Regex = LiCOSOnline
    .stateRegex(
      s"""(?:myCharacter|character|(?:(?:role#${RoleValidation.roleNamesInLowerCase}(?:/board#$idStringPattern)?|extensionalDisclosureRange|votingResultsSummary#${CharacterValidation.idStringPattern}|votingResultsDetails#${CharacterValidation.idStringPattern}-${CharacterValidation.idStringPattern})/)?character#$idStringPattern)"""
    )
    .r
  val `@id`:  Reads[String] = pattern(`@idRegex`)
  val gender: Reads[String] = pattern("""(?:fe)?male""".r)
  val image:  Reads[String] = pattern(WerewolfWorld.characterIcon("""(?:[a-z]|anonymous)""").r)
  val id:     Reads[Int]    = min(1) keepAnd max(15)
  val status: Reads[String] = pattern("""(?:alive|dead|death by (?:execution|attack|fear)|unnatural death)""".r)
  val result: Reads[String] = pattern("""(?:win|lose)""".r)
}
