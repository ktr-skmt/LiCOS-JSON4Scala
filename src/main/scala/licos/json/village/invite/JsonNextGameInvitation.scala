package licos.json.village.invite

import play.api.libs.json.{Json, OFormat}

case class JsonNextGameInvitation(`type`: String,
                                  villageId: Long) {
  def this(villageId: Long) = {
    this(
      JsonNextGameInvitation.`type`,
      villageId)
  }
}

object JsonNextGameInvitation {
  implicit val jsonFormat: OFormat[JsonNextGameInvitation] = Json.format[JsonNextGameInvitation]

  val `type`: String = "nextGameInvitation"

  def apply(villageId: Long): JsonNextGameInvitation = new JsonNextGameInvitation(villageId: Long)
}