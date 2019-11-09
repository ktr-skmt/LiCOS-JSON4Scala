package licos.json.element.village

import licos.bson.element.village.character.BsonRoleCharacter
import licos.bson.element.village.{BsonBase, BsonScroll}
import licos.json.element.Element
import licos.json.element.village.character.JsonRoleCharacter
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonScroll private (base: JsonBase, sub: JsonSubScroll) extends JsonElement with Element {
  def this(
      base:         JsonBase,
      myCharacter:  JsonRoleCharacter,
      nodeId:       String,
      scrollTop:    Int,
      scrollHeight: Int,
      offsetHeight: Int
  ) = {
    this(
      base: JsonBase,
      JsonSubScroll(
        myCharacter:  JsonRoleCharacter,
        nodeId:       String,
        scrollTop:    Int,
        scrollHeight: Int,
        offsetHeight: Int
      )
    )
  }

  def myCharacter:  JsonRoleCharacter = sub.myCharacter
  def nodeId:       String            = sub.nodeId
  def scrollTop:    Int               = sub.scrollTop
  def scrollHeight: Int               = sub.scrollHeight
  def offsetHeight: Int               = sub.offsetHeight

  override def toBson: BsonScroll = {
    new BsonScroll(
      new ObjectId(),
      base.toBson:        BsonBase,
      myCharacter.toBson: BsonRoleCharacter,
      nodeId:             String,
      scrollTop:          Int,
      scrollHeight:       Int,
      offsetHeight:       Int
    )
  }
}

object JsonScroll {
  def apply(
      base:         JsonBase,
      myCharacter:  JsonRoleCharacter,
      nodeId:       String,
      scrollTop:    Int,
      scrollHeight: Int,
      offsetHeight: Int
  ): JsonScroll = {
    new JsonScroll(
      base:         JsonBase,
      myCharacter:  JsonRoleCharacter,
      nodeId:       String,
      scrollTop:    Int,
      scrollHeight: Int,
      offsetHeight: Int
    )
  }

  implicit val jsonFormat: Format[JsonScroll] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubScroll]
  )(JsonScroll.apply, unlift(JsonScroll.unapply))
}

final case class JsonSubScroll(
    myCharacter:  JsonRoleCharacter,
    nodeId:       String,
    scrollTop:    Int,
    scrollHeight: Int,
    offsetHeight: Int
)

object JsonSubScroll {
  implicit val jsonFormat: OFormat[JsonSubScroll] = Json.format[JsonSubScroll]
}
