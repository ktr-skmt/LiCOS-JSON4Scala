package licos.json.element.village.role

import licos.bson.element.village.BsonName
import licos.bson.element.village.role.BsonSimpleRole
import licos.json.element.village.JsonName
import licos.json.element.village.iri.RoleContext
import licos.json.validation.village.RoleValidation
import org.bson.types.ObjectId

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
final case class JsonSimpleRole(`@context`: String, `@id`: String, name: JsonName, image: String)
    extends JsonAbstractRole(`@context`: String, `@id`: String, name: JsonName, image: String) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(`@id`: String, name: JsonName, image: String) = {
    this(RoleContext.iri: String, `@id`: String, name: JsonName, image: String)
  }

  def toBson: BsonSimpleRole = {
    new BsonSimpleRole(
      new ObjectId(),
      `@context`:  String,
      `@id`:       String,
      name.toBson: BsonName,
      image:       String
    )
  }
}

object JsonSimpleRole {

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonSimpleRole] = (
    (JsPath \ "@context").read[String](RoleValidation.`@context`) and
      (JsPath \ "@id").read[String](RoleValidation.`@id`) and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](RoleValidation.image)
  )(JsonSimpleRole.apply _)

  implicit val jsonWrites: OWrites[JsonSimpleRole] = Json.writes[JsonSimpleRole]
}
