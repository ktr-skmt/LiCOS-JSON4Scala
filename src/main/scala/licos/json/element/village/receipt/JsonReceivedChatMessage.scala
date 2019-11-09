package licos.json.element.village.receipt

import licos.json.element.Element
import licos.json.element.village.JsonChatFromServer

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
  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonReceivedChatMessage] = (
    (JsPath \ "type").read[String](pattern("""receivedChatMessage""".r)) and
      (JsPath \ "token").read[String](pattern("""[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}""".r)) and
      (JsPath \ "villageId").read[Long](min(0L)) and
      (JsPath \ "serverTimestamp").read[String](pattern("""[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}(\\+[0-9]{2}:[0-9]{2}|Z)""".r)) and
      (JsPath \ "clientTimestamp").read[String](pattern("""[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}(\\+[0-9]{2}:[0-9]{2}|Z)""".r))
    )(JsonReceivedChatMessage.apply _)

  implicit val jsonWrites: OWrites[JsonReceivedChatMessage] = Json.writes[JsonReceivedChatMessage]

  val `type`: String = "receivedChatMessage"
}
