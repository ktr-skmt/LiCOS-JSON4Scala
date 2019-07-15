package licos.json.village

import licos.bson.village.BsonAvatar
import licos.json.village.iri.AvatarContext
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonAvatar(`@context`: String,
                      `@id`: String,
                      token: String,
                      name: String,
                      image: String) extends JsonElement {
  def this(`@id`: String,
           token: String,
           name: String,
           image: String) = {
    this(
      AvatarContext.iri: String,
      `@id`: String,
      token: String,
      name: String,
      image: String
    )
  }

  override def toBson: BsonAvatar = {
    new BsonAvatar(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      token: String,
      name: String,
      image: String
    )
  }
}

object JsonAvatar {
  implicit val jsonFormat: OFormat[JsonAvatar] = Json.format[JsonAvatar]

  def apply(`@id`: String,
            token: String,
            name: String,
            image: String): JsonAvatar = {
    new JsonAvatar(
      `@id`: String,
      token: String,
      name: String,
      image: String
    )
  }
}