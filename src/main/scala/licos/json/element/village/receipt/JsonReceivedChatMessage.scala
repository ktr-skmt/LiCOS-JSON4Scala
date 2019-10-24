package licos.json.element.village.receipt

import licos.json.element.village.JsonChatFromServer
import play.api.libs.json.{Json, OFormat}

case class JsonReceivedChatMessage(`type`: String,
                                   token: String,
                                   villageId: Long,
                                   serverTimestamp: String,
                                   clientTimestamp: String) extends JsonReceivedMessage(`type`, token, villageId) {
  override protected def validType: String = JsonReceivedChatMessage.`type`

  def this(chat: JsonChatFromServer) = {
    this(
      JsonReceivedChatMessage.`type`,
      chat.base.token,
      chat.base.village.id,
      chat.base.serverTimestamp,
      chat.base.clientTimestamp)
  }

  //override def key: String = JsonReceivedPlayerMessage.key(
  //  villageId: Long,
  //  token: String,
  //  serverTimestamp: String,
  //  clientTimestamp: String)

  //def read(config: Configuration, log: LoggingAdapter): Option[JsValue] = {
  //  JsonReceivedPlayerMessage.read(villageId, token, serverTimestamp, clientTimestamp, config, log)
  //}
}

object JsonReceivedChatMessage {
  implicit val jsonFormat: OFormat[JsonReceivedChatMessage] = Json.format[JsonReceivedChatMessage]

  val `type`: String = "receivedChatMessage"
}