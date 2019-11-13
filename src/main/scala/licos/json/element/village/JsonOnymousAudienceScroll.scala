package licos.json.element.village

import licos.bson.element.village.{BsonAvatar, BsonBase, BsonOnymousAudienceScroll}
import licos.json.element.Element
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath}

final case class JsonOnymousAudienceScroll private (base: JsonBase, sub: JsonSubOnymousAudienceScroll)
    extends JsonElement
    with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
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

  implicit val jsonFormat: Format[JsonOnymousAudienceScroll] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubOnymousAudienceScroll]
  )(JsonOnymousAudienceScroll.apply, unlift(JsonOnymousAudienceScroll.unapply))
}

final case class JsonSubOnymousAudienceScroll(
    avatar:       JsonAvatar,
    nodeId:       String,
    scrollTop:    Int,
    scrollHeight: Int,
    offsetHeight: Int
)

object JsonSubOnymousAudienceScroll {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonSubOnymousAudienceScroll] = (
    (JsPath \ "avatar").read[JsonAvatar] and
      (JsPath \ "nodeId").read[String] and
      (JsPath \ "scrollTop").read[Int] and
      (JsPath \ "scrollHeight").read[Int] and
      (JsPath \ "offsetHeight").read[Int]
  )(JsonSubOnymousAudienceScroll.apply _)

  implicit val jsonWrites: OWrites[JsonSubOnymousAudienceScroll] = Json.writes[JsonSubOnymousAudienceScroll]
}
