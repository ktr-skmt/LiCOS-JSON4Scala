package licos.json.element.village

import java.util.{List => JList}

import licos.bson.element.village.{BsonBase, BsonChatFromServer, BsonFlavorText}
import licos.json.element.Element
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

import scala.collection.JavaConverters._

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonFlavorText private (base: JsonBase, sub: JsonSubFlavorText) extends JsonElement with Element {
  def this(base: JsonBase, flavorText: JList[JsonChatFromServer]) = {
    this(
      base: JsonBase,
      new JsonSubFlavorText(
        flavorText: JList[JsonChatFromServer]
      )
    )
  }
  override def toBson: BsonFlavorText = {
    new BsonFlavorText(
      new ObjectId(),
      base.toBson:                         BsonBase,
      sub.flavorText.map(_.toBson).asJava: JList[BsonChatFromServer]
    )
  }
}

object JsonFlavorText {
  implicit val jsonFormat: Format[JsonFlavorText] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubFlavorText]
  )(JsonFlavorText.apply, unlift(JsonFlavorText.unapply))
}

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonSubFlavorText(flavorText: Seq[JsonChatFromServer]) {
  def this(flavorText: JList[JsonChatFromServer]) = {
    this(
      flavorText.asScala: Seq[JsonChatFromServer]
    )
  }
}

object JsonSubFlavorText {
  implicit val jsonFormat: OFormat[JsonSubFlavorText] = Json.format[JsonSubFlavorText]
}
