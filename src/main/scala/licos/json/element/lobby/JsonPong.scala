package licos.json.element.lobby

import licos.json.validation.village.AvatarValidation

/**
  * <pre>
  * Created on 2018/01/12.
  * </pre>
  *
  * @author K.Sakamoto
  */
final case class JsonPong(`type`: String, token: String, id: String) extends TypeSystem(`type`) {
  override protected def validType: String = `type`
}

object JsonPong {

  val `type`: String = "pong"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonPong] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "id").read[String](AvatarValidation.token)
  )(JsonPong.apply _)

  implicit val jsonWrites: OWrites[JsonPong] = Json.writes[JsonPong]
}
