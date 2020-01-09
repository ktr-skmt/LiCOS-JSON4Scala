package licos.json.element.village.receipt

import licos.json.element.village.server2client.JsonFlavorText
import licos.json.validation.village.{AvatarValidation, TimeValidation, VillageValidation}

final case class JsonReceivedFlavorTextMessage(`type`: String, token: String, villageId: Long, phase: String, day: Int)
    extends JsonReceivedMessage(`type`, token, villageId) {

  override protected def validType: String = JsonReceivedFlavorTextMessage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, villageId: Long, phase: String, day: Int) = {
    this(JsonReceivedFlavorTextMessage.`type`, token, villageId, phase, day)
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(flavorText: JsonFlavorText) = {
    this(
      JsonReceivedFlavorTextMessage.`type`,
      flavorText.base.token,
      flavorText.base.village.id,
      flavorText.base.phase,
      flavorText.base.day
    )
  }
}

object JsonReceivedFlavorTextMessage {
  val `type`: String = "receivedFlavorTextMessage"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonReceivedFlavorTextMessage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "villageId").read[Long](VillageValidation.id) and
      (JsPath \ "phase").read[String](TimeValidation.phase) and
      (JsPath \ "day").read[Int](TimeValidation.day)
  )(JsonReceivedFlavorTextMessage.apply _)

  implicit val jsonWrites: OWrites[JsonReceivedFlavorTextMessage] = Json.writes[JsonReceivedFlavorTextMessage]

}
