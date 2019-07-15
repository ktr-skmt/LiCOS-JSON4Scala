package licos.json.village

import licos.bson.village.{BsonBase, BsonScroll}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonScroll private (base: JsonBase,
                               sub: JsonSubScroll) extends JsonElement {
  def this(base: JsonBase,
           nodeId: String,
           scrollTop: Int,
           scrollHeight: Int,
           offsetHeight: Int) = {
    this(
      base: JsonBase,
      JsonSubScroll(
        nodeId: String,
        scrollTop: Int,
        scrollHeight: Int,
        offsetHeight: Int
      )
    )
  }

  def nodeId: String = sub.nodeId
  def scrollTop: Int = sub.scrollTop
  def scrollHeight: Int = sub.scrollHeight
  def offsetHeight: Int = sub.offsetHeight

  override def toBson: BsonScroll = {
    new BsonScroll(
      new ObjectId(),
      base.toBson: BsonBase,
      nodeId: String,
      scrollTop: Int,
      scrollHeight: Int,
      offsetHeight: Int
    )
  }
}

object JsonScroll {
  def apply(base: JsonBase,
            nodeId: String,
            scrollTop: Int,
            scrollHeight: Int,
            offsetHeight: Int): JsonScroll = {
    new JsonScroll(
      base: JsonBase,
      nodeId: String,
      scrollTop: Int,
      scrollHeight: Int,
      offsetHeight: Int)
  }

  implicit val jsonFormat: Format[JsonScroll] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubScroll]
    )(JsonScroll.apply, unlift(JsonScroll.unapply))
}

case class JsonSubScroll(nodeId: String,
                         scrollTop: Int,
                         scrollHeight: Int,
                         offsetHeight: Int)

object JsonSubScroll {
  implicit val jsonFormat: OFormat[JsonSubScroll] = Json.format[JsonSubScroll]
}