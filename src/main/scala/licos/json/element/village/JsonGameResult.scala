package licos.json.element.village

import java.util.{List => JList}

import licos.bson.element.village.{BsonBase, BsonGameResult}
import licos.bson.element.village.character.BsonResultCharacter
import licos.bson.element.village.role.BsonResultRole
import licos.json.element.Element
import licos.json.element.village.character.JsonResultCharacter
import licos.json.element.village.role.JsonResultRole
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

import scala.collection.JavaConverters._

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonGameResult private (base: JsonBase, sub: JsonSubGameResult) extends JsonElement with Element {
  def this(base: JsonBase, character: Seq[JsonResultCharacter], role: Seq[JsonResultRole]) = {
    this(
      base: JsonBase,
      JsonSubGameResult(
        character.sortWith { (a1: JsonResultCharacter, a2: JsonResultCharacter) =>
          a1.name.en < a2.name.en
        }.sortBy(!_.isMine): Seq[JsonResultCharacter],
        role.sortWith { (r1: JsonResultRole, r2: JsonResultRole) =>
          r1.name.en < r2.name.en
        }: Seq[JsonResultRole]
      )
    )
  }

  def this(base: JsonBase, character: JList[JsonResultCharacter], role: JList[JsonResultRole]) = {
    this(
      base: JsonBase,
      character.asScala.sortWith { (a1: JsonResultCharacter, a2: JsonResultCharacter) =>
        a1.name.en < a2.name.en
      }.sortBy(!_.isMine): Seq[JsonResultCharacter],
      role.asScala.sortWith { (r1: JsonResultRole, r2: JsonResultRole) =>
        r1.name.en < r2.name.en
      }: Seq[JsonResultRole]
    )
  }

  def character: Seq[JsonResultCharacter] =
    sub.character.sortWith { (a1: JsonResultCharacter, a2: JsonResultCharacter) =>
      a1.name.en < a2.name.en
    }.sortBy(!_.isMine)
  def role: Seq[JsonResultRole] = sub.role.sortWith { (r1: JsonResultRole, r2: JsonResultRole) =>
    r1.name.en < r2.name.en
  }

  override def toBson: BsonGameResult = {
    new BsonGameResult(
      new ObjectId(),
      base.toBson:                    BsonBase,
      character.map(_.toBson).asJava: JList[BsonResultCharacter],
      role.map(_.toBson).asJava:      JList[BsonResultRole]
    )
  }
}

object JsonGameResult {
  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def apply(base: JsonBase, character: Seq[JsonResultCharacter], role: Seq[JsonResultRole]): JsonGameResult = {
    new JsonGameResult(
      base: JsonBase,
      character.sortWith { (a1: JsonResultCharacter, a2: JsonResultCharacter) =>
        a1.name.en < a2.name.en
      }.sortBy(!_.isMine): Seq[JsonResultCharacter],
      role.sortWith { (r1: JsonResultRole, r2: JsonResultRole) =>
        r1.name.en < r2.name.en
      }: Seq[JsonResultRole]
    )
  }

  implicit val jsonFormat: Format[JsonGameResult] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubGameResult]
  )(JsonGameResult.apply, unlift(JsonGameResult.unapply))
}

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonSubGameResult(character: Seq[JsonResultCharacter], role: Seq[JsonResultRole])

object JsonSubGameResult {
  implicit val jsonFormat: OFormat[JsonSubGameResult] = Json.format[JsonSubGameResult]
}
