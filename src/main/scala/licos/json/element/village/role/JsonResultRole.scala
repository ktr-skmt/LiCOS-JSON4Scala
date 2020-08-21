package licos.json.element.village.role

import java.util.{List => JList}

import licos.json.element.village.JsonName
import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.element.village.iri.RoleContext
import licos.json.validation.village.RoleValidation

import scala.jdk.CollectionConverters._

final case class JsonResultRole(
    `@context`:      String,
    `@id`:           String,
    isMine:          Boolean,
    name:            JsonName,
    image:           String,
    numberOfPlayers: Int,
    character:       Seq[JsonSimpleCharacter]
) extends JsonAbstractRole(
      `@context`: String,
      `@id`:      String,
      name:       JsonName,
      image:      String
    ) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@id`:           String,
      isMine:          Boolean,
      name:            JsonName,
      image:           String,
      numberOfPlayers: Int,
      character:       Seq[JsonSimpleCharacter]
  ) = {
    this(
      RoleContext.iri: String,
      `@id`:           String,
      isMine:          Boolean,
      name:            JsonName,
      image:           String,
      numberOfPlayers: Int,
      character:       Seq[JsonSimpleCharacter]
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@context`:      String,
      `@id`:           String,
      isMine:          Boolean,
      name:            JsonName,
      image:           String,
      numberOfPlayers: Int,
      character:       JList[JsonSimpleCharacter]
  ) = {
    this(
      `@context`:               String,
      `@id`:                    String,
      isMine:                   Boolean,
      name:                     JsonName,
      image:                    String,
      numberOfPlayers:          Int,
      character.asScala.toList: Seq[JsonSimpleCharacter]
    )
  }

}

object JsonResultRole {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonResultRole] = (
    (JsPath \ "@context").read[String](RoleValidation.`@context`) and
      (JsPath \ "@id").read[String](RoleValidation.`@id`) and
      (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](RoleValidation.image) and
      (JsPath \ "numberOfPlayers").read[Int](RoleValidation.numberOfPlayers) and
      (JsPath \ "character").read[Seq[JsonSimpleCharacter]]
  )(JsonResultRole.apply _)

  implicit val jsonWrites: OWrites[JsonResultRole] = Json.writes[JsonResultRole]
}
