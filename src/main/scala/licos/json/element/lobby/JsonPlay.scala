package licos.json.element.lobby

import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonPlay(`type`: String, token: String, villageId: Long) extends TypeSystem(`type`) {
  override protected def validType: String = JsonPlay.`type`
}

object JsonPlay {

  val `type`: String = "play"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonPlay] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "villageId").read[Long](VillageValidation.id)
  )(JsonPlay.apply _)

  implicit val jsonWrites: OWrites[JsonPlay] = Json.writes[JsonPlay]

}
