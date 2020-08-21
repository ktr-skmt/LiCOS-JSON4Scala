package licos.json.element.village.server2client

import java.util.{List => JList}

import licos.json.element.Element
import licos.json.element.village.character.JsonCharacter
import licos.json.element.village.role.JsonRole
import licos.json.element.village.{JsonBase, JsonElement}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

import scala.jdk.CollectionConverters._

final case class JsonPhase private (base: JsonBase, sub: JsonSubPhase) extends JsonElement with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(base: JsonBase, character: Seq[JsonCharacter], role: Seq[JsonRole]) = {
    this(
      base: JsonBase,
      JsonSubPhase(
        character.sortWith { (a1: JsonCharacter, a2: JsonCharacter) =>
          a1.name.en < a2.name.en
        }.sortBy(!_.isMine): Seq[JsonCharacter],
        role.sortWith { (r1: JsonRole, r2: JsonRole) =>
          r1.name.en < r2.name.en
        }: Seq[JsonRole]
      )
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(base: JsonBase, character: JList[JsonCharacter], role: JList[JsonRole]) = {
    this(
      base: JsonBase,
      character.asScala.sortWith { (a1: JsonCharacter, a2: JsonCharacter) =>
        a1.name.en < a2.name.en
      }.sortBy(!_.isMine).toList: Seq[JsonCharacter],
      role.asScala.sortWith { (r1: JsonRole, r2: JsonRole) =>
        r1.name.en < r2.name.en
      }.toList: Seq[JsonRole]
    )
  }

  def character: Seq[JsonCharacter] =
    sub.character.sortWith { (a1: JsonCharacter, a2: JsonCharacter) =>
      a1.name.en < a2.name.en
    }.sortBy(!_.isMine)

  def role: Seq[JsonRole] = sub.role.sortWith { (r1: JsonRole, r2: JsonRole) =>
    r1.name.en < r2.name.en
  }

  def exceptExtensionalDisclosureRange: JsonPhase = {
    JsonPhase(
      base.exceptExtensionalDisclosureRange: JsonBase,
      sub:                                   JsonSubPhase
    )
  }
}

object JsonPhase {
  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: Format[JsonPhase] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubPhase]
  )(JsonPhase.apply, unlift(JsonPhase.unapply))

}

final case class JsonSubPhase(character: Seq[JsonCharacter], role: Seq[JsonRole])

object JsonSubPhase {
  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: OFormat[JsonSubPhase] = Json.format[JsonSubPhase]
}
