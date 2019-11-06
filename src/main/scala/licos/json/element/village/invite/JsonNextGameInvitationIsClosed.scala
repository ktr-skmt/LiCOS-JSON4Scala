package licos.json.element.village.invite

import licos.json.element.lobby.TypeSystem
import play.api.libs.json.{Json, OFormat}

case class JsonNextGameInvitationIsClosed(`type`: String) extends TypeSystem(`type`) {

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
