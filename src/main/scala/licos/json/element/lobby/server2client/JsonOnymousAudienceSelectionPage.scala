package licos.json.element.lobby.server2client

import licos.json.element.lobby.TypeSystem

final case class JsonOnymousAudienceSelectionPage(`type`: String, avatar: Seq[JsonSubAvatarInfo])
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonOnymousAudienceSelectionPage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(avatar: Seq[JsonSubAvatarInfo]) = {
    this(JsonOnymousAudienceSelectionPage.`type`, avatar)
  }
}

object JsonOnymousAudienceSelectionPage {

  val `type`: String = "onymousAudienceSelectionPage"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonOnymousAudienceSelectionPage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "avatar").read[Seq[JsonSubAvatarInfo]]
  )(JsonOnymousAudienceSelectionPage.apply _)

  implicit val jsonWrites: OWrites[JsonOnymousAudienceSelectionPage] = Json.writes[JsonOnymousAudienceSelectionPage]

}
