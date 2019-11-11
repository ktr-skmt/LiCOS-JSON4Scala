package licos.json.validation.lobby

object UserValidation {
  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  val name:     Reads[String] = pattern("""[A-Za-z][!-~]{4,14}""".r)
  val password: Reads[String] = minLength[String](8) keepAnd maxLength[String](128)
}
