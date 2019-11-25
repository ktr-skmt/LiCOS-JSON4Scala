package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonSelectVillage(`type`: String, token: String, villageId: Long) extends TypeSystem(`type`) {

  override protected def validType: String = JsonSelectVillage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, villageId: Long) = {
    this(JsonSelectVillage.`type`, token, villageId)
  }
}

object JsonSelectVillage {

  val `type`: String = "selectVillage"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSelectVillage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "villageId").read[Long](VillageValidation.id)
  )(JsonSelectVillage.apply _)

  implicit val jsonWrites: OWrites[JsonSelectVillage] = Json.writes[JsonSelectVillage]

}
