package licos.json.element.village

import licos.bson.element.village.{BsonAvatar, BsonBase, BsonChatText, BsonOnymousAudienceChat}
import licos.json.element.Element
import licos.json.validation.village.ChatValidation
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath}

final case class JsonOnymousAudienceChat(base: JsonBase, sub: JsonSubOnymousAudienceChat)
    extends JsonElement
    with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      base:                         JsonBase,
      avatar:                       JsonAvatar,
      isMine:                       Boolean,
      text:                         JsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isFromServer:                 Boolean
  ) {
    this(
      base: JsonBase,
      JsonSubOnymousAudienceChat(
        avatar:                       JsonAvatar,
        isMine:                       Boolean,
        text:                         JsonChatText,
        maxLengthOfUnicodeCodePoints: Int,
        isFromServer:                 Boolean
      )
    )
  }

  def avatar:                       JsonAvatar   = sub.avatar
  def isMine:                       Boolean      = sub.isMine
  def text:                         JsonChatText = sub.text
  def maxLengthOfUnicodeCodePoints: Int          = sub.maxLengthOfUnicodeCodePoints
  def isFromServer:                 Boolean      = sub.isFromServer

  override def toBson: BsonOnymousAudienceChat = {
    new BsonOnymousAudienceChat(
      new ObjectId(),
      base.toBson:                  BsonBase,
      avatar.toBson:                BsonAvatar,
      isMine:                       Boolean,
      text.toBson:                  BsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isFromServer:                 Boolean
    )
  }
}

object JsonOnymousAudienceChat {

  implicit val jsonFormat: Format[JsonOnymousAudienceChat] = (
    JsPath.format[JsonBase] and JsPath.format[JsonSubOnymousAudienceChat]
  )(JsonOnymousAudienceChat.apply, unlift(JsonOnymousAudienceChat.unapply))
}

final case class JsonSubOnymousAudienceChat(
    avatar:                       JsonAvatar,
    isMine:                       Boolean,
    text:                         JsonChatText,
    maxLengthOfUnicodeCodePoints: Int,
    isFromServer:                 Boolean
)

object JsonSubOnymousAudienceChat {

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonSubOnymousAudienceChat] = (
    (JsPath \ "avatar").read[JsonAvatar] and
      (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "text").read[JsonChatText] and
      (JsPath \ "maxLengthOfUnicodeCodePoints").read[Int](ChatValidation.maxLengthOfUnicodeCodePoints) and
      (JsPath \ "isFromServer").read[Boolean]
  )(JsonSubOnymousAudienceChat.apply _)

  implicit val jsonWrites: OWrites[JsonSubOnymousAudienceChat] = Json.writes[JsonSubOnymousAudienceChat]
}
