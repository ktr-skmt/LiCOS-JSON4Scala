package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonReady(`type`: String, token: String, villageId: Long) extends TypeSystem(`type`) {

  override protected def validType: String = JsonReady.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, villageId: Long) = {
    this(JsonReady.`type`, token, villageId)
  }
}

object JsonReady {

  val `type`: String = "ready"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonReady] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "villageId").read[Long](VillageValidation.id)
  )(JsonReady.apply _)

  implicit val jsonWrites: OWrites[JsonReady] = Json.writes[JsonReady]

}
