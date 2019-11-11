package licos.json.element.lobby

import licos.json.validation.village.{AvatarValidation, VillageValidation}

/**
  * <pre>
  * Created on 2018/01/04.
  * </pre>
  *
  * @author K.Sakamoto
  */
final case class JsonSelectVillage(`type`: String, token: String, villageId: Long) extends TypeSystem(`type`) {
  override protected def validType: String = JsonSelectVillage.`type`
}

object JsonSelectVillage {

  val `type`: String = "selectVillage"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonSelectVillage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "villageId").read[Long](VillageValidation.id)
  )(JsonSelectVillage.apply _)

  implicit val jsonWrites: OWrites[JsonSelectVillage] = Json.writes[JsonSelectVillage]

}
