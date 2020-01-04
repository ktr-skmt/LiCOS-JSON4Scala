package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonStopRobotPlayer(`type`: String, token: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonStopRobotPlayer.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String) = {
    this(JsonStopRobotPlayer.`type`, token)
  }

}

object JsonStopRobotPlayer {

  val `type`: String = "stopRobotPlayer"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonStopRobotPlayer] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token)
  )(JsonStopRobotPlayer.apply _)

  implicit val jsonWrites: OWrites[JsonStopRobotPlayer] = Json.writes[JsonStopRobotPlayer]

}
