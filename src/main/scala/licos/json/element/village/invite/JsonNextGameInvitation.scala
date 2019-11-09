package licos.json.element.village.invite

import licos.json.element.Element
import licos.json.element.lobby.TypeSystem
import play.api.libs.json.{Json, OFormat}

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonNextGameInvitation(`type`: String, villageId: Long) extends TypeSystem(`type`) with Element {

  override protected def validType: String = JsonNextGameInvitation.`type`

  def this(villageId: Long) = {
    this(JsonNextGameInvitation.`type`, villageId)
  }
}

object JsonNextGameInvitation {

  implicit val jsonFormat: OFormat[JsonNextGameInvitation] = Json.format[JsonNextGameInvitation]

  val `type`: String = "nextGameInvitation"

  def apply(villageId: Long): JsonNextGameInvitation = new JsonNextGameInvitation(villageId: Long)
}
