package licos.json.element.village.role

import licos.bson.element.village.BsonName
import licos.bson.element.village.role.BsonSimpleRole
import licos.json.element.village.JsonName
import licos.json.element.village.iri.RoleContext
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonSimpleRole(`@context`: String, `@id`: String, name: JsonName, image: String)
    extends JsonAbstractRole(`@context`: String, `@id`: String, name: JsonName, image: String) {

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
  implicit val jsonFormat: OFormat[JsonSimpleRole] = Json.format[JsonSimpleRole]

  def apply(`@id`: String, name: JsonName, image: String): JsonSimpleRole = {
    new JsonSimpleRole(
      `@id`: String,
      name:  JsonName,
      image: String
    )
  }
}
