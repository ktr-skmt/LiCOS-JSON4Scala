package licos.json.village.receipt

import licos.json.village.JsonChatFromServer
import play.api.libs.json.{Json, OFormat}

case class JsonReceivedPlayerMessage(`type`: String,
                                     token: String,
                                     villageId: Long,
                                     serverTimestamp: String,
                                     clientTimestamp: String) extends JsonReceivedMessage(`type`, token, villageId) {
  override protected def validType: String = JsonReceivedPlayerMessage.`type`

  def this(chat: JsonChatFromServer) = {
    this(
      JsonReceivedPlayerMessage.`type`,
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

object JsonReceivedPlayerMessage {
  implicit val jsonFormat: OFormat[JsonReceivedPlayerMessage] = Json.format[JsonReceivedPlayerMessage]

  val `type`: String = "receivedPlayerMessage"
}