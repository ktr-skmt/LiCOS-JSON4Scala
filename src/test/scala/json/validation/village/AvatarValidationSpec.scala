package json.validation.village

import licos.json.validation.village.AvatarValidation
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

final class AvatarValidationSpec extends AnyFlatSpec with Matchers {
  "Avatar @id" should "fully match the regex of Avatar @id" in {
    "https://licos.online/state/0.3/village#3/avatar" should fullyMatch regex AvatarValidation.`@idRegex`
    "https://licos.online/state/0.3/village#3/character#1/avatar" should fullyMatch regex AvatarValidation.`@idRegex`
  }
}
