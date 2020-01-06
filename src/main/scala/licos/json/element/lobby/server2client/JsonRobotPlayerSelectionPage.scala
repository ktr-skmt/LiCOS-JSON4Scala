package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem
import licos.json.validation.lobby.RobotPlayerInfoValidation
import play.api.libs.json.{Json, OWrites, Reads}

final case class JsonRobotPlayerSelectionPage(`type`: String, avatar: Seq[JsonRobotPlayerInfo])
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonRobotPlayerSelectionPage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(avatar: Seq[JsonRobotPlayerInfo]) = {
    this(JsonRobotPlayerSelectionPage.`type`, avatar)
  }

}

object JsonRobotPlayerSelectionPage {

  val `type`: String = "robotPlayerSelectionPage"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json.JsPath

  implicit val jsonReads: Reads[JsonRobotPlayerSelectionPage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "avatar").read[Seq[JsonRobotPlayerInfo]]
  )(JsonRobotPlayerSelectionPage.apply _)

  implicit val jsonWrites: OWrites[JsonRobotPlayerSelectionPage] = Json.writes[JsonRobotPlayerSelectionPage]
}

final case class JsonRobotPlayerInfo private (
    avatar:           JsonSubAvatarInfo,
    status:           String,
    isAuthorized:     Boolean,
    isTestPassed:     Boolean,
    isFullyAutomated: Boolean
) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      token:            String,
      name:             String,
      image:            String,
      lang:             String,
      status:           String,
      isAuthorized:     Boolean,
      isTestPassed:     Boolean,
      isFullyAutomated: Boolean
  ) = {
    this(JsonSubAvatarInfo(token, name, image, lang), status, isAuthorized, isTestPassed, isFullyAutomated)
  }

  def token: String = avatar.token
  def name:  String = avatar.name
  def image: String = avatar.image
  def lang:  String = avatar.lang

}

object JsonRobotPlayerInfo {

  import play.api.libs.functional.syntax._
  import play.api.libs.json.JsPath

  implicit val jsonReads: Reads[JsonRobotPlayerInfo] = (
    JsPath.read[JsonSubAvatarInfo] and
      (JsPath \ "status").read[String](RobotPlayerInfoValidation.status) and
      (JsPath \ "isAuthorized").read[Boolean] and
      (JsPath \ "isTestPassed").read[Boolean] and
      (JsPath \ "isFullyAutomated").read[Boolean]
  )(JsonRobotPlayerInfo.apply _)

  implicit val jsonWrites: OWrites[JsonRobotPlayerInfo] = Json.writes[JsonRobotPlayerInfo]

}
