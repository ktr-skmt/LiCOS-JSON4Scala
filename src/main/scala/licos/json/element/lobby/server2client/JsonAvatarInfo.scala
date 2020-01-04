package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonAvatarInfo(`type`: String, token: String, name: String, image: String, lang: String)
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonAvatarInfo.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, name: String, image: String, lang: String) = {
    this(JsonAvatarInfo.`type`, token, name, image, lang)
  }
}

object JsonAvatarInfo {
  val `type`: String = "avatar"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonAvatarInfo] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "name").read[String](AvatarValidation.name) and
      (JsPath \ "image").read[String](AvatarValidation.image) and
      (JsPath \ "lang").read[String](VillageValidation.language)
  )(JsonAvatarInfo.apply _)

  implicit val jsonWrites: OWrites[JsonAvatarInfo] = Json.writes[JsonAvatarInfo]

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
