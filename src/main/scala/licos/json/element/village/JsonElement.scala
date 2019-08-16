package licos.json.element.village

import licos.bson.element.village.BsonElement

trait JsonElement {
  def toBson: BsonElement
}
