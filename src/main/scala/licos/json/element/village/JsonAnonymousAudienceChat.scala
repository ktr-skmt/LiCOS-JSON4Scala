package licos.json.element.village

import licos.bson.element.village.{BsonAnonymousAudienceChat, BsonBase, BsonChatText}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonAnonymousAudienceChat(base: JsonBase, sub: JsonSubAnonymousAudienceChat) extends JsonElement {
  def this(base:                         JsonBase,
           isMine:                       Boolean,
           text:                         JsonChatText,
           maxLengthOfUnicodeCodePoints: Int,
           isFromServer:                 Boolean) {
    this(
      base: JsonBase,
      JsonSubAnonymousAudienceChat(
        isMine:                       Boolean,
        text:                         JsonChatText,
        maxLengthOfUnicodeCodePoints: Int,
        isFromServer:                 Boolean
      )
    )
  }

  def isMine:                       Boolean      = sub.isMine
  def text:                         JsonChatText = sub.text
  def maxLengthOfUnicodeCodePoints: Int          = sub.maxLengthOfUnicodeCodePoints
  def isFromServer:                 Boolean      = sub.isFromServer

  override def toBson: BsonAnonymousAudienceChat = {
    new BsonAnonymousAudienceChat(
      new ObjectId(),
      base.toBson:                  BsonBase,
      isMine:                       Boolean,
      text.toBson:                  BsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isFromServer:                 Boolean
    )
  }
}

object JsonAnonymousAudienceChat {

  def apply(base:                         JsonBase,
            isMine:                       Boolean,
            text:                         JsonChatText,
            maxLengthOfUnicodeCodePoints: Int,
            isFromServer:                 Boolean): JsonAnonymousAudienceChat = {
    new JsonAnonymousAudienceChat(
      base:                         JsonBase,
      isMine:                       Boolean,
      text:                         JsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isFromServer:                 Boolean
    )
  }

  implicit val jsonFormat: Format[JsonAnonymousAudienceChat] = (
    JsPath.format[JsonBase] and JsPath.format[JsonSubAnonymousAudienceChat]
  )(JsonAnonymousAudienceChat.apply, unlift(JsonAnonymousAudienceChat.unapply))
}

case class JsonSubAnonymousAudienceChat(isMine:                       Boolean,
                                        text:                         JsonChatText,
                                        maxLengthOfUnicodeCodePoints: Int,
                                        isFromServer:                 Boolean)

object JsonSubAnonymousAudienceChat {
  implicit val jsonFormat: OFormat[JsonSubAnonymousAudienceChat] = Json.format[JsonSubAnonymousAudienceChat]
}
