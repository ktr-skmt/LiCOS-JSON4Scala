package licos.json.element.village

import licos.bson.element.village.agent.BsonSimpleAgent
import licos.bson.element.village.BsonBoardPolarity
import licos.json.element.village.agent.JsonSimpleAgent
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonBoardPolarity(`@context`: String,
                             `@id`: String,
                             agent: JsonSimpleAgent,
                             polarity: String,
                             phase: String,
                             date: Int) extends JsonElement {
  override def toBson: BsonBoardPolarity = {
    new BsonBoardPolarity(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      agent.toBson: BsonSimpleAgent,
      polarity: String,
      phase: String,
      date: Int
    )
  }
}

object JsonBoardPolarity {
  implicit val jsonFormat: OFormat[JsonBoardPolarity] = Json.format[JsonBoardPolarity]
}