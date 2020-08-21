package licos.json.element.auth.robot2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, BaseValidation}

final case class JsonAuthenticationAndAuthorizationRequest(
    `type`:       String,
    userEmail:    String,
    userPassword: String,
    robotVersion: String,
    accessToken:  String,
    sourceCode:   JsonSourceCode
) extends TypeSystem(`type`) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      userEmail:    String,
      userPassword: String,
      robotVersion: String,
      accessToken:  String,
      sourceCode:   JsonSourceCode
  ) = {
    this(
      JsonAuthenticationAndAuthorizationRequest.`type`,
      userEmail,
      userPassword,
      robotVersion,
      accessToken,
      sourceCode
    )
  }

  override protected def validType: String = JsonAuthenticationAndAuthorizationRequest.`type`
}

object JsonAuthenticationAndAuthorizationRequest {
  val `type`: String = "authenticationAndAuthorizationRequest"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.{email, maxLength, minLength, pattern}
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonAuthenticationAndAuthorizationRequest] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "userEmail").read[String](email) and
      (JsPath \ "userPassword").read[String](minLength[String](5) keepAnd maxLength[String](256)) and
      (JsPath \ "robotVersion").read[String](pattern("""^[0-9]+\.[0-9]+\.[0-9]+.*""".r)) and
      (JsPath \ "accessToken").read[String](AvatarValidation.token) and
      (JsPath \ "sourceCode").read[JsonSourceCode]
  )(JsonAuthenticationAndAuthorizationRequest.apply _)

  implicit val jsonWrites: OWrites[JsonAuthenticationAndAuthorizationRequest] =
    Json.writes[JsonAuthenticationAndAuthorizationRequest]
}

final case class JsonSourceCode(timestamp: String, programmingLanguage: Seq[JsonProgrammingLanguage], url: String)

object JsonSourceCode {
  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonSourceCode] = (
    (JsPath \ "timestamp").read[String](pattern(BaseValidation.timestampRegex)) and
      (JsPath \ "programmingLanguage").read[Seq[JsonProgrammingLanguage]] and
      (JsPath \ "url").read[String](pattern("""^https://.+""".r))
  )(JsonSourceCode.apply _)

  implicit val jsonWrites: OWrites[JsonSourceCode] = Json.writes[JsonSourceCode]
}

final case class JsonProgrammingLanguage(name: String, version: String)

object JsonProgrammingLanguage {
  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.{maxLength, minLength}
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonProgrammingLanguage] = (
    (JsPath \ "name").read[String](minLength[String](1) keepAnd maxLength[String](Int.MaxValue)) and
      (JsPath \ "version").read[String](minLength[String](1) keepAnd maxLength[String](Int.MaxValue))
  )(JsonProgrammingLanguage.apply _)

  implicit val jsonWrites: OWrites[JsonProgrammingLanguage] = Json.writes[JsonProgrammingLanguage]
}
