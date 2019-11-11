package licos.json.element.village

import licos.bson.element.village.character.BsonRoleCharacter
import licos.bson.element.village.{BsonBase, BsonScroll}
import licos.json.element.Element
import licos.json.element.village.character.JsonRoleCharacter
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath}

final case class JsonScroll private (base: JsonBase, sub: JsonSubScroll) extends JsonElement with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
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

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonSubScroll] = (
    (JsPath \ "myCharacter").read[JsonRoleCharacter] and
      (JsPath \ "nodeId").read[String] and
      (JsPath \ "scrollTop").read[Int] and
      (JsPath \ "scrollHeight").read[Int] and
      (JsPath \ "offsetHeight").read[Int]
  )(JsonSubScroll.apply _)

  implicit val jsonWrites: OWrites[JsonSubScroll] = Json.writes[JsonSubScroll]
}
