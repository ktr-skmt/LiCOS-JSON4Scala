package licos.json.element.village.receipt

import licos.json.element.Element
import licos.json.element.village.JsonPhase
import play.api.libs.json.{Json, OFormat}

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
  implicit val jsonFormat: OFormat[JsonReceivedSystemMessage] = Json.format[JsonReceivedSystemMessage]

  val `type`: String = "receivedSystemMessage"
}
