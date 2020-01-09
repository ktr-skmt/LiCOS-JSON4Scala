package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonAvatarInfo private (`type`: String, subAvatarInfo: JsonSubAvatarInfo) extends TypeSystem(`type`) {

  override protected def validType: String = JsonAvatarInfo.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, name: String, image: String, language: String) = {
    this(JsonAvatarInfo.`type`, JsonSubAvatarInfo(token, name, image, language))
  }

  def token:    String = subAvatarInfo.token
  def name:     String = subAvatarInfo.name
  def image:    String = subAvatarInfo.image
  def language: String = subAvatarInfo.language
}

object JsonAvatarInfo {
  val `type`: String = "avatar"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonAvatarInfo] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      JsPath.read[JsonSubAvatarInfo]
  )(JsonAvatarInfo.apply _)

  implicit val jsonWrites: Writes[JsonAvatarInfo] = (o: JsonAvatarInfo) => {
    Json.obj(
      "type" -> o.`type`,
      "token" -> o.token,
      "name" -> o.name,
      "image" -> o.image,
      "language" -> o.language
    )
  }

}

final case class JsonSubAvatarInfo(token: String, name: String, image: String, language: String)

object JsonSubAvatarInfo {

  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSubAvatarInfo] = (
    (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "name").read[String](AvatarValidation.name) and
      (JsPath \ "image").read[String](AvatarValidation.image) and
      (JsPath \ "language").read[String](VillageValidation.language)
  )(JsonSubAvatarInfo.apply _)

  implicit val jsonWrites: OWrites[JsonSubAvatarInfo] = Json.writes[JsonSubAvatarInfo]
}

final case class JsonGetAvatarInfo(`type`: String, token: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonGetAvatarInfo.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String) = {
    this(JsonGetAvatarInfo.`type`, token)
  }
}

object JsonGetAvatarInfo {
  val `type`: String = "getAvatar"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonGetAvatarInfo] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token)
  )(JsonGetAvatarInfo.apply _)

  implicit val jsonWrites: OWrites[JsonGetAvatarInfo] = Json.writes[JsonGetAvatarInfo]
}
