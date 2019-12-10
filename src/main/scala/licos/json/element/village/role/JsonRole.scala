package licos.json.element.village.role

import java.util.{List => JList}

import licos.json.element.village.iri.RoleContext
import licos.json.element.village.{JsonBoardResult, JsonName}
import licos.json.validation.village.RoleValidation

import scala.collection.JavaConverters._

final case class JsonRole(
    `@context`:      String,
    `@id`:           String,
    name:            JsonName,
    image:           String,
    isMine:          Boolean,
    numberOfPlayers: Int,
    board:           Seq[JsonBoardResult]
) extends JsonAbstractRole(
      `@context`: String,
      `@id`:      String,
      name:       JsonName,
      image:      String
    ) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@context`:      String,
      `@id`:           String,
      name:            JsonName,
      image:           String,
      isMine:          Boolean,
      numberOfPlayers: Int,
      board:           JList[JsonBoardResult]
  ) = {
    this(
      `@context`:      String,
      `@id`:           String,
      name:            JsonName,
      image:           String,
      isMine:          Boolean,
      numberOfPlayers: Int,
      board.asScala:   Seq[JsonBoardResult]
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@id`:           String,
      name:            JsonName,
      image:           String,
      isMine:          Boolean,
      numberOfPlayers: Int,
      board:           Seq[JsonBoardResult]
  ) = {
    this(
      RoleContext.iri: String,
      `@id`:           String,
      name:            JsonName,
      image:           String,
      isMine:          Boolean,
      numberOfPlayers: Int,
      board:           Seq[JsonBoardResult]
    )
  }

}

object JsonRole {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonRole] = (
    (JsPath \ "@context").read[String](RoleValidation.`@context`) and
      (JsPath \ "@id").read[String](RoleValidation.`@id`) and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](RoleValidation.image) and
      (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "numberOfPlayers").read[Int](RoleValidation.numberOfPlayers) and
      (JsPath \ "board").read[Seq[JsonBoardResult]]
  )(JsonRole.apply _)

  implicit val jsonWrites: OWrites[JsonRole] = Json.writes[JsonRole]
}
