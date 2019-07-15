package licos.json.village.invite

import play.api.libs.json.{Json, OFormat}

case class JsonNextGameInvitationIsClosed(`type`: String) {
  def this() = {
    this(JsonNextGameInvitationIsClosed.`type`)
  }
}

object JsonNextGameInvitationIsClosed {
  implicit val jsonFormat: OFormat[JsonNextGameInvitationIsClosed] = Json.format[JsonNextGameInvitationIsClosed]

  val `type`: String = "nextGameInvitationIsClosed"

  def apply(): JsonNextGameInvitationIsClosed = new JsonNextGameInvitationIsClosed()
}