package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonRunRobotPlayerInTheBackground(`type`: String, token: Seq[String]) extends TypeSystem(`type`) {

  override protected def validType: String = JsonRunRobotPlayerInTheBackground.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: Seq[String]) = {
    this(JsonRunRobotPlayerInTheBackground.`type`, token)
  }

}

object JsonRunRobotPlayerInTheBackground {

  val `type`: String = "runRobotPlayerInTheBackground"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonRunRobotPlayerInTheBackground] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[Seq[String]](Reads.seq[String](AvatarValidation.token))
  )(JsonRunRobotPlayerInTheBackground.apply _)

  implicit val jsonWrites: OWrites[JsonRunRobotPlayerInTheBackground] = Json.writes[JsonRunRobotPlayerInTheBackground]

}
