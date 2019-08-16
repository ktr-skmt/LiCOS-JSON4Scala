package licos.json.element.village.receipt

import licos.json.element.village.JsonPhase
import play.api.libs.json.{Json, OFormat}

case class JsonReceivedSystemMessage(`type`: String,
                                     token: String,
                                     villageId: Long,
                                     phase: String,
                                     date: Int) extends JsonReceivedMessage(`type`, token, villageId) {
  override protected def validType: String = JsonReceivedSystemMessage.`type`

  def this(phase: JsonPhase) = {
    this(
      JsonReceivedSystemMessage.`type`,
      phase.base.token,
      phase.base.village.id,
      phase.base.phase,
      phase.base.date
    )
  }

  //override def key: String = JsonReceivedSystemMessage.key(
  //  villageId: Long,
  //  token: String,
  //  phase: String,
  //  date: Int)

  //def read(config: Configuration, log: LoggingAdapter): Option[JsValue] = {
  //  JsonReceivedSystemMessage.read(villageId, token, phase, date, config, log)
  //}
}

object JsonReceivedSystemMessage {
  implicit val jsonFormat: OFormat[JsonReceivedSystemMessage] = Json.format[JsonReceivedSystemMessage]

  val `type`: String = "receivedSystemMessage"
}