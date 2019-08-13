package licos.json.village

import licos.bson.village.agent.BsonRoleAgent
import licos.bson.village.{BsonBase, BsonStar, BsonStarInfo}
import licos.json.village.agent.JsonRoleAgent
import org.bson.types.ObjectId
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath, Json, OFormat}

case class JsonStar(base: JsonBase,
                    sub: JsonSubStar) extends JsonElement {

  override def toBson: BsonStar = {
    new BsonStar(
      new ObjectId(),
      base.toBson: BsonBase,
      sub.myAgent.toBson: BsonRoleAgent,
      sub.star.toBson: BsonStarInfo
    )
  }
}

object JsonStar {
  implicit val jsonFormat: Format[JsonStar] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubStar]
    )(JsonStar.apply, unlift(JsonStar.unapply))
}

case class JsonSubStar(myAgent: JsonRoleAgent,
                       star: JsonStarInfo)

object JsonSubStar {
  implicit val jsonFormat: OFormat[JsonSubStar] = Json.format[JsonSubStar]
}

case class JsonStarInfo(`@context`: String,
                        `@id`: String,
                        token: String,
                        serverTimestamp: String,
                        clientTimestamp: String,
                        isMarked: Boolean) extends JsonElement {

  override def toBson: BsonStarInfo = {
    new BsonStarInfo(
      new ObjectId(),
      `@context`: String,
      `@id`: String,
      token: String,
      serverTimestamp: String,
      clientTimestamp: String,
      isMarked: Boolean
    )
  }
}

object JsonStarInfo {
  implicit val jsonFormat: OFormat[JsonStarInfo] = Json.format[JsonStarInfo]
}