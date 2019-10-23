package licos.json.element.village.receipt

import licos.json.element.village.JsonFlavorText
import play.api.libs.json.{Json, OFormat}

case class JsonReceivedFlavorTextMessage(`type`: String,
                                         token: String,
                                         villageId: Long,
                                         phase: String,
                                         day: Int) extends JsonReceivedMessage(`type`, token, villageId) {
  override protected def validType: String = JsonReceivedFlavorTextMessage.`type`

  def this(flavorText: JsonFlavorText) = {
    this(
      JsonReceivedFlavorTextMessage.`type`,
      flavorText.base.token,
      flavorText.base.village.id,
      flavorText.base.phase,
      flavorText.base.day
    )
  }

  //override def key: String = JsonReceivedFlavorTextMessage.key(
  //  villageId: Long,
  //  token: String,
  //  phase: String,
  //  date: Int)

  //def read(config: Configuration, log: LoggingAdapter): Option[JsValue] = {
  //  JsonReceivedFlavorTextMessage.read(villageId, token, phase, date, config, log)
  //}
}

object JsonReceivedFlavorTextMessage {
  implicit val jsonFormat: OFormat[JsonReceivedFlavorTextMessage] = Json.format[JsonReceivedFlavorTextMessage]

  val `type`: String = "receivedFlavorTextMessage"
}