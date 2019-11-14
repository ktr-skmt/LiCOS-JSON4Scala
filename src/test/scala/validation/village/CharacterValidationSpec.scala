package validation.village

import licos.json.validation.village.CharacterValidation
import org.scalatest.{FlatSpec, Matchers}

class CharacterValidationSpec extends FlatSpec with Matchers {
  "Character @id" should "fully match the regex of Character @id" in {
    "https://licos.online/state/0.3/village#3/myCharacter" should fullyMatch regex CharacterValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/character" should fullyMatch regex CharacterValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/character#1" should fullyMatch regex CharacterValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/role#villager/character#1" should fullyMatch regex CharacterValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/role#villager/board#1/character#1" should fullyMatch regex CharacterValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/votingResultsSummary#1/character#1" should fullyMatch regex CharacterValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/votingResultsDetails#1-2/character#2" should fullyMatch regex CharacterValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/extensionalDisclosureRange/character#2" should fullyMatch regex CharacterValidation.`@idRegex`
  }
}
