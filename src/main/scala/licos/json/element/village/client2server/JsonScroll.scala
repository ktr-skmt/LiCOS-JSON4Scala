package licos.json.element.village.client2server

import licos.json.element.Element
import licos.json.element.village.character.JsonRoleCharacter
import licos.json.element.village.{JsonBase, JsonElement}
import licos.json.validation.village.ScrollValidation
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

  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSubScroll] = (
    (JsPath \ "myCharacter").read[JsonRoleCharacter] and
      (JsPath \ "nodeId").read[String](ScrollValidation.nodeId) and
      (JsPath \ "scrollTop").read[Int](ScrollValidation.scrollTop) and
      (JsPath \ "scrollHeight").read[Int](ScrollValidation.scrollHeight) and
      (JsPath \ "offsetHeight").read[Int](ScrollValidation.offsetHeight)
  )(JsonSubScroll.apply _)

  implicit val jsonWrites: OWrites[JsonSubScroll] = Json.writes[JsonSubScroll]
}
