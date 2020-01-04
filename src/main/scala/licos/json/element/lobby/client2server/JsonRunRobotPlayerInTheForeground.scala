package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonRunRobotPlayerInTheForeground(`type`: String, token: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonRunRobotPlayerInTheForeground.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String) = {
    this(JsonRunRobotPlayerInTheBackground.`type`, token)
  }

}

object JsonRunRobotPlayerInTheForeground {

  val `type`: String = "runRobotPlayerInTheForeground"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonRunRobotPlayerInTheForeground] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token)
  )(JsonRunRobotPlayerInTheForeground.apply _)

  implicit val jsonWrites: OWrites[JsonRunRobotPlayerInTheForeground] = Json.writes[JsonRunRobotPlayerInTheForeground]

}
