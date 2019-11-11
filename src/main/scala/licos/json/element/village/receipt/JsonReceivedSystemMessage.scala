package licos.json.element.village.receipt

import licos.json.element.Element
import licos.json.element.village.JsonPhase
import licos.json.validation.village.{AvatarValidation, TimeValidation, VillageValidation}

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonReceivedSystemMessage(`type`: String, token: String, villageId: Long, phase: String, day: Int)
    extends JsonReceivedMessage(`type`, token, villageId)
    with Element {
  override protected def validType: String = JsonReceivedSystemMessage.`type`

  def this(phase: JsonPhase) = {
    this(
      JsonReceivedSystemMessage.`type`,
      phase.base.token,
      phase.base.village.id,
      phase.base.phase,
      phase.base.day
    )
  }

}

object JsonReceivedSystemMessage {

  val `type`: String = "receivedSystemMessage"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonReceivedSystemMessage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "villageId").read[Long](VillageValidation.id) and
      (JsPath \ "phase").read[String](TimeValidation.phase) and
      (JsPath \ "day").read[Int](TimeValidation.day)
  )(JsonReceivedSystemMessage.apply _)

  implicit val jsonWrites: OWrites[JsonReceivedSystemMessage] = Json.writes[JsonReceivedSystemMessage]
}
