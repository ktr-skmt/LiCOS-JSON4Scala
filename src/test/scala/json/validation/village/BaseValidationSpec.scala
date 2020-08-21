package json.validation.village

import licos.json.validation.village.BaseValidation
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

final class BaseValidationSpec extends AnyFlatSpec with Matchers {
  "Timestamp" should "fully match the regex of timestamp" in {
    "2006-10-07T12:06:56.568+09:00" should fullyMatch regex BaseValidation.timestampRegex
  }
}
