package licos.json.village

import licos.bson.village.BsonElement

trait JsonElement {
  def toBson: BsonElement
}
