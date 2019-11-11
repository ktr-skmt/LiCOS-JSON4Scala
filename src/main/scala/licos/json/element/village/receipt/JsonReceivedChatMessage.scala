package licos.json.element.village.receipt

import licos.json.element.Element
import licos.json.element.village.JsonChatFromServer
import licos.json.validation.village.{AvatarValidation, BaseValidation, VillageValidation}

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonReceivedChatMessage(
    `type`:          String,
    token:           String,
    villageId:       Long,
    serverTimestamp: String,
    clientTimestamp: String
) extends JsonReceivedMessage(`type`, token, villageId)
    with Element {
  override protected def validType: String = JsonReceivedChatMessage.`type`

  def this(chat: JsonChatFromServer) = {
    this(
      JsonReceivedChatMessage.`type`,
      chat.base.token,
      chat.base.village.id,
      chat.base.serverTimestamp,
      chat.base.clientTimestamp
    )
  }
}

object JsonReceivedChatMessage {
  val `type`: String = "receivedChatMessage"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonReceivedChatMessage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "villageId").read[Long](VillageValidation.id) and
      (JsPath \ "serverTimestamp").read[String](BaseValidation.serverTimestamp) and
      (JsPath \ "clientTimestamp").read[String](BaseValidation.clientTimestamp)
  )(JsonReceivedChatMessage.apply _)

  implicit val jsonWrites: OWrites[JsonReceivedChatMessage] = Json.writes[JsonReceivedChatMessage]
}
