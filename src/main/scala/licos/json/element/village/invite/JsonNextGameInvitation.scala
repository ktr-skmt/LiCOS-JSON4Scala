package licos.json.element.village.invite

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.VillageValidation

final case class JsonNextGameInvitation(`type`: String, villageId: Long) extends TypeSystem(`type`) {

  override protected def validType: String = JsonNextGameInvitation.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(villageId: Long) = {
    this(JsonNextGameInvitation.`type`, villageId)
  }
}

object JsonNextGameInvitation {
  val `type`: String = "nextGameInvitation"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonNextGameInvitation] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "villageId").read[Long](VillageValidation.id)
  )(JsonNextGameInvitation.apply _)

  implicit val jsonWrites: OWrites[JsonNextGameInvitation] = Json.writes[JsonNextGameInvitation]

}
