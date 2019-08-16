package licos.json.element.village

import licos.bson.element.village.{BsonAudienceChat, BsonAvatar, BsonBase, BsonChatText}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonAudienceChat(base: JsonBase,
                            sub: JsonSubAudienceChat) extends JsonElement {
  def this(base: JsonBase,
           avatar: Option[JsonAvatar],
           isMine: Boolean,
           text: JsonChatText,
           characterLimit: Int,
           isFromServer: Boolean) {
    this(
      base: JsonBase,
      JsonSubAudienceChat(
        avatar: Option[JsonAvatar],
        isMine: Boolean,
        text: JsonChatText,
        characterLimit: Int,
        isFromServer: Boolean
      )
    )
  }

  def avatarOpt: Option[JsonAvatar] = sub.avatar
  def isMine: Boolean = sub.isMine
  def text: JsonChatText = sub.text
  def characterLimit: Int = sub.characterLimit
  def isFromServer: Boolean = sub.isFromServer

  override def toBson: BsonAudienceChat = {
    new BsonAudienceChat(
      new ObjectId(),
      base.toBson: BsonBase,
      avatarOpt.map(_.toBson): Option[BsonAvatar],
      isMine: Boolean,
      text.toBson: BsonChatText,
      characterLimit: Int,
      isFromServer: Boolean
    )
  }
}

object JsonAudienceChat {

  def apply(base: JsonBase,
            avatar: Option[JsonAvatar],
            isMine: Boolean,
            text: JsonChatText,
            characterLimit: Int,
            isFromServer: Boolean): JsonAudienceChat = {
    new JsonAudienceChat(
      base: JsonBase,
      avatar: Option[JsonAvatar],
      isMine: Boolean,
      text: JsonChatText,
      characterLimit: Int,
      isFromServer: Boolean
    )
  }

  implicit val jsonFormat: Format[JsonAudienceChat] = (
    JsPath.format[JsonBase] and JsPath.format[JsonSubAudienceChat]
  )(JsonAudienceChat.apply, unlift(JsonAudienceChat.unapply))
}

case class JsonSubAudienceChat(avatar: Option[JsonAvatar],
                               isMine: Boolean,
                               text: JsonChatText,
                               characterLimit: Int,
                               isFromServer: Boolean)

object JsonSubAudienceChat {
  implicit val jsonFormat: OFormat[JsonSubAudienceChat] = Json.format[JsonSubAudienceChat]
}