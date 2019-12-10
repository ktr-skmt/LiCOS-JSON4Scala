package licos.json.element.village.server2client

import licos.json.element.Element
import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.element.village.{JsonBase, JsonChatText, JsonElement}
import licos.json.validation.village.ChatValidation
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath}

final case class JsonChatFromServer private (base: JsonBase, sub: JsonSubChatFromServer)
    extends JsonElement
    with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      base:                         JsonBase,
      character:                    JsonSimpleCharacter,
      isMine:                       Boolean,
      id:                           Int,
      counter:                      Int,
      maxNumberOfChatMessages:      Int,
      interval:                     Int,
      text:                         JsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isOver:                       Boolean
  ) = {
    this(
      base,
      JsonSubChatFromServer(
        character:                    JsonSimpleCharacter,
        isMine:                       Boolean,
        id:                           Int,
        counter:                      Int,
        maxNumberOfChatMessages:      Int,
        interval:                     Int,
        text:                         JsonChatText,
        maxLengthOfUnicodeCodePoints: Int,
        isOver:                       Boolean
      )
    )
  }

  def fromHostPlayerToAvatar(guid: String): JsonChatFromServer = {
    import cats.implicits._
    def isCharacterMine: Boolean = guid === base.token
    JsonChatFromServer(
      base.otherAvatar(guid): JsonBase,
      JsonSubChatFromServer(
        character:                    JsonSimpleCharacter,
        isCharacterMine:              Boolean,
        id:                           Int,
        counter:                      Int,
        maxNumberOfChatMessages:      Int,
        interval:                     Int,
        text:                         JsonChatText,
        maxLengthOfUnicodeCodePoints: Int,
        isOver:                       Boolean
      )
    )
  }

  def character:                    JsonSimpleCharacter = sub.character
  def isMine:                       Boolean             = sub.isMine
  def id:                           Int                 = sub.id
  def counter:                      Int                 = sub.counter
  def maxNumberOfChatMessages:      Int                 = sub.maxNumberOfChatMessages
  def interval:                     Int                 = sub.interval
  def text:                         JsonChatText        = sub.text
  def maxLengthOfUnicodeCodePoints: Int                 = sub.maxLengthOfUnicodeCodePoints
  def isOver:                       Boolean             = sub.isOver

  def appendText(t: String): JsonChatFromServer = {
    JsonChatFromServer(
      base: JsonBase,
      JsonSubChatFromServer(
        character:               JsonSimpleCharacter,
        isMine:                  Boolean,
        id:                      Int,
        counter:                 Int,
        maxNumberOfChatMessages: Int,
        interval:                Int,
        JsonChatText(
          s"""${text.`@value`}
             |
             |$t""".stripMargin,
          text.`@language`: String
        ),
        maxLengthOfUnicodeCodePoints: Int,
        isOver:                       Boolean
      )
    )
  }

}

object JsonChatFromServer {

  implicit val jsonFormat: Format[JsonChatFromServer] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubChatFromServer]
  )(JsonChatFromServer.apply, unlift(JsonChatFromServer.unapply))
}

final case class JsonSubChatFromServer(
    character:                    JsonSimpleCharacter,
    isMine:                       Boolean,
    id:                           Int,
    counter:                      Int,
    maxNumberOfChatMessages:      Int,
    interval:                     Int,
    text:                         JsonChatText,
    maxLengthOfUnicodeCodePoints: Int,
    isOver:                       Boolean
)

object JsonSubChatFromServer {

  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSubChatFromServer] = (
    (JsPath \ "character").read[JsonSimpleCharacter] and
      (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "id").read[Int](ChatValidation.id) and
      (JsPath \ "counter").read[Int](ChatValidation.counter) and
      (JsPath \ "maxNumberOfChatMessages").read[Int](ChatValidation.maxNumberOfChatMessages) and
      (JsPath \ "interval").read[Int](ChatValidation.interval) and
      (JsPath \ "text").read[JsonChatText] and
      (JsPath \ "maxLengthOfUnicodeCodePoints").read[Int](ChatValidation.maxLengthOfUnicodeCodePoints) and
      (JsPath \ "isOver").read[Boolean]
  )(JsonSubChatFromServer.apply _)

  implicit val jsonWrites: OWrites[JsonSubChatFromServer] = Json.writes[JsonSubChatFromServer]
}
