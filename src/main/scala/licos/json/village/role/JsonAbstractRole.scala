package licos.json.village.role

import licos.json.village.{JsonElement, JsonName}

abstract class JsonAbstractRole(`@context`: String,
                                `@id`: String,
                                name: JsonName,
                                image: String) extends JsonElement
