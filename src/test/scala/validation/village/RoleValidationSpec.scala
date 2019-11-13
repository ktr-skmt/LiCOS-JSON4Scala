package validation.village

import licos.json.validation.village.RoleValidation
import org.scalatest.{FlatSpec, Matchers}

class RoleValidationSpec extends FlatSpec with Matchers {
  "Role @id" should "fully match the regex of Role @id" in {
    "https://licos.online/state/0.3/village#3/role" should fullyMatch regex RoleValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/character#1/role" should fullyMatch regex RoleValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/role#villager" should fullyMatch regex RoleValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/extensionalDisclosureRange/character#10/role" should fullyMatch regex RoleValidation.`@idRegex`
  }
}
