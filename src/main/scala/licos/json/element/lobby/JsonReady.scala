package licos.json.element.lobby

import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonReady(`type`: String, token: String, villageId: Long) extends TypeSystem(`type`) {
  override protected def validType: String = JsonReady.`type`
}

object JsonReady {

  val `type`: String = "ready"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonReady] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "villageId").read[Long](VillageValidation.id)
  )(JsonReady.apply _)

  implicit val jsonWrites: OWrites[JsonReady] = Json.writes[JsonReady]

}
