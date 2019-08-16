package licos.json.element.village

import licos.bson.element.village.BsonUpdate
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonUpdate(`@id`: String,
                      phase: String,
                      date: Int) extends JsonElement {
  override def toBson: BsonUpdate = {
    new BsonUpdate(
      new ObjectId(),
      `@id`: String,
      phase: String,
      date: Int
    )
  }
}

object JsonUpdate {
  implicit val jsonFormat: OFormat[JsonUpdate] = Json.format[JsonUpdate]
}
