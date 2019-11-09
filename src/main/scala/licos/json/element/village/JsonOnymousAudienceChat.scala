package licos.json.element.village

import licos.bson.element.village.{BsonAvatar, BsonBase, BsonChatText, BsonOnymousAudienceChat}
import licos.json.element.Element
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonOnymousAudienceChat(base: JsonBase, sub: JsonSubOnymousAudienceChat)
    extends JsonElement
    with Element {
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

  def apply(
      base:                         JsonBase,
      avatar:                       JsonAvatar,
      isMine:                       Boolean,
      text:                         JsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isFromServer:                 Boolean
  ): JsonOnymousAudienceChat = {
    new JsonOnymousAudienceChat(
      base:                         JsonBase,
      avatar:                       JsonAvatar,
      isMine:                       Boolean,
      text:                         JsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isFromServer:                 Boolean
    )
  }

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
  implicit val jsonFormat: OFormat[JsonSubOnymousAudienceChat] = Json.format[JsonSubOnymousAudienceChat]
}
