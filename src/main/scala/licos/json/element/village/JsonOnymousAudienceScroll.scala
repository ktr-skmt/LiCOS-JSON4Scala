package licos.json.element.village

import licos.bson.element.village.{BsonAvatar, BsonBase, BsonOnymousAudienceScroll}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonOnymousAudienceScroll private (base: JsonBase, sub: JsonSubOnymousAudienceScroll) extends JsonElement {
  def this(base: JsonBase, avatar: JsonAvatar, nodeId: String, scrollTop: Int, scrollHeight: Int, offsetHeight: Int) = {
    this(
      base: JsonBase,
      JsonSubOnymousAudienceScroll(
        avatar:       JsonAvatar,
        nodeId:       String,
        scrollTop:    Int,
        scrollHeight: Int,
        offsetHeight: Int
      )
    )
  }

  def avatar:       JsonAvatar = sub.avatar
  def nodeId:       String     = sub.nodeId
  def scrollTop:    Int        = sub.scrollTop
  def scrollHeight: Int        = sub.scrollHeight
  def offsetHeight: Int        = sub.offsetHeight

  override def toBson: BsonOnymousAudienceScroll = {
    new BsonOnymousAudienceScroll(
      new ObjectId(),
      base.toBson:   BsonBase,
      avatar.toBson: BsonAvatar,
      nodeId:        String,
      scrollTop:     Int,
      scrollHeight:  Int,
      offsetHeight:  Int
    )
  }
}

object JsonOnymousAudienceScroll {
  def apply(base:         JsonBase,
            avatar:       JsonAvatar,
            nodeId:       String,
            scrollTop:    Int,
            scrollHeight: Int,
            offsetHeight: Int): JsonOnymousAudienceScroll = {
    new JsonOnymousAudienceScroll(
      base:         JsonBase,
      avatar:       JsonAvatar,
      nodeId:       String,
      scrollTop:    Int,
      scrollHeight: Int,
      offsetHeight: Int)
  }

  implicit val jsonFormat: Format[JsonOnymousAudienceScroll] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubOnymousAudienceScroll]
  )(JsonOnymousAudienceScroll.apply, unlift(JsonOnymousAudienceScroll.unapply))
}

case class JsonSubOnymousAudienceScroll(avatar:       JsonAvatar,
                                        nodeId:       String,
                                        scrollTop:    Int,
                                        scrollHeight: Int,
                                        offsetHeight: Int)

object JsonSubOnymousAudienceScroll {
  implicit val jsonFormat: OFormat[JsonSubOnymousAudienceScroll] = Json.format[JsonSubOnymousAudienceScroll]
}
