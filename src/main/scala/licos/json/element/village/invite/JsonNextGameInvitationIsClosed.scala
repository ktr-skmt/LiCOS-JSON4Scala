package licos.json.element.village.invite

import licos.json.element.Element
import licos.json.element.lobby.TypeSystem
import play.api.libs.json.{Json, OFormat}

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonNextGameInvitationIsClosed(`type`: String) extends TypeSystem(`type`) with Element {

  override protected def validType: String = JsonNextGameInvitationIsClosed.`type`

  def this() = {
    this(JsonNextGameInvitationIsClosed.`type`)
  }
}

object JsonNextGameInvitationIsClosed {
  implicit val jsonFormat: OFormat[JsonNextGameInvitationIsClosed] = Json.format[JsonNextGameInvitationIsClosed]

  val `type`: String = "nextGameInvitationIsClosed"

  def apply(): JsonNextGameInvitationIsClosed = new JsonNextGameInvitationIsClosed()
}
