package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem

final case class JsonChangeUserEmail(`type`: String, userEmail: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonChangeUserEmail.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(userEmail: String) = {
    this(JsonChangeUserEmail.`type`, userEmail)
  }
}

object JsonChangeUserEmail {

  val `type`: String = "changeUserEmail"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.{email, pattern}
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonChangeUserEmail] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "userEmail").read[String](email)
  )(JsonChangeUserEmail.apply _)

  implicit val jsonWrites: OWrites[JsonChangeUserEmail] = Json.writes[JsonChangeUserEmail]

}
