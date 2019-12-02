package licos.json.element.village.server2client

import java.util.{List => JList}

import licos.bson.element.village.{BsonBase, BsonChatFromServer, BsonFlavorText}
import licos.json.element.Element
import licos.json.element.village.{JsonBase, JsonElement}
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath}

import scala.collection.JavaConverters._

final case class JsonFlavorText private (base: JsonBase, sub: JsonSubFlavorText) extends JsonElement with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(base: JsonBase, flavorText: Seq[JsonChatFromServer]) = {
    this(
      base: JsonBase,
      JsonSubFlavorText(
        flavorText: Seq[JsonChatFromServer]
      )
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(base: JsonBase, flavorText: JList[JsonChatFromServer]) = {
    this(
      base: JsonBase,
      new JsonSubFlavorText(
        flavorText: JList[JsonChatFromServer]
      )
    )
  }

  def flavorText: Seq[JsonChatFromServer] = sub.flavorText

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

final case class JsonSubFlavorText(flavorText: Seq[JsonChatFromServer]) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(flavorText: JList[JsonChatFromServer]) = {
    this(
      flavorText.asScala: Seq[JsonChatFromServer]
    )
  }
}

object JsonSubFlavorText {

  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonSubFlavorText] =
    (JsPath \ "flavorText").read[Seq[JsonChatFromServer]].map(JsonSubFlavorText.apply)

  implicit val jsonWrites: OWrites[JsonSubFlavorText] = Json.writes[JsonSubFlavorText]
}
