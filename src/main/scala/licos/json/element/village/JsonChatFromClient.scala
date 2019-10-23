package licos.json.element.village

import licos.bson.element.village.{BsonBase, BsonChatFromClient, BsonChatText}
import licos.bson.element.village.character.{BsonRoleCharacter, BsonSimpleCharacter}
import licos.json.element.village.character.{JsonRoleCharacter, JsonSimpleCharacter}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonChatFromClient private (base: JsonBase,
                                       sub: JsonSubChatFromClient) extends JsonElement {
  def this(base: JsonBase,
           myCharacter: JsonRoleCharacter,
           character: JsonSimpleCharacter,
           isMine: Boolean,
           text: JsonChatText,
           maxLengthOfUnicodeCodePoints: Int,
           isOver: Boolean) = {
    this(
      base: JsonBase,
      JsonSubChatFromClient(
        myCharacter: JsonRoleCharacter,
        character: JsonSimpleCharacter,
        isMine: Boolean,
        text: JsonChatText,
        maxLengthOfUnicodeCodePoints: Int,
        isOver: Boolean
      )
    )
  }

  def myCharacter: JsonRoleCharacter = sub.myCharacter
  def character: JsonSimpleCharacter = sub.character
  def isMine: Boolean = sub.isMine
  def text: JsonChatText = sub.text
  def maxLengthOfUnicodeCodePoints: Int = sub.maxLengthOfUnicodeCodePoints
  def isOver: Boolean = sub.isOver

  override def toBson: BsonChatFromClient = {
    new BsonChatFromClient(
      new ObjectId(),
      base.toBson: BsonBase,
      myCharacter.toBson: BsonRoleCharacter,
      character.toBson: BsonSimpleCharacter,
      isMine: Boolean,
      text.toBson: BsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isOver: Boolean
    )
  }
}

object JsonChatFromClient {
  def apply(base: JsonBase,
            myCharacter: JsonRoleCharacter,
            character: JsonSimpleCharacter,
            isMine: Boolean,
            text: JsonChatText,
            maxLengthOfUnicodeCodePoints: Int,
            isOver: Boolean): JsonChatFromClient = {
    new JsonChatFromClient(
      base: JsonBase,
      myCharacter: JsonRoleCharacter,
      character: JsonSimpleCharacter,
      isMine: Boolean,
      text: JsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isOver: Boolean)
  }

  implicit val jsonFormat: Format[JsonChatFromClient] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubChatFromClient]
    )(JsonChatFromClient.apply, unlift(JsonChatFromClient.unapply))
}

case class JsonSubChatFromClient(myCharacter: JsonRoleCharacter,
                                 character: JsonSimpleCharacter,
                                 isMine: Boolean,
                                 text: JsonChatText,
                                 maxLengthOfUnicodeCodePoints: Int,
                                 isOver: Boolean)

object JsonSubChatFromClient {
  implicit val jsonFormat: OFormat[JsonSubChatFromClient] = Json.format[JsonSubChatFromClient]
}