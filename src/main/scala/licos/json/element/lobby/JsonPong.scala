package licos.json.element.lobby

import licos.json.validation.village.AvatarValidation

final case class JsonPong(`type`: String, token: String, id: String) extends TypeSystem(`type`) {

  override protected def validType: String = `type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, id: String) = {
    this(JsonPong.`type`, token, id)
  }
}

object JsonPong {

  val `type`: String = "pong"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonPong] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "id").read[String](AvatarValidation.token)
  )(JsonPong.apply _)

  implicit val jsonWrites: OWrites[JsonPong] = Json.writes[JsonPong]
}
