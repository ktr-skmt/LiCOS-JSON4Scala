package licos.json.village.invite

import licos.json.lobby.TypeSystem
import play.api.libs.json.{Json, OFormat}

case class JsonNextGameInvitation(`type`: String,
                                  villageId: Long) extends TypeSystem(`type`) {

  override protected def validType: String = JsonNextGameInvitation.`type`

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