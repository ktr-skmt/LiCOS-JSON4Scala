package licos.json.element.village.agent

import licos.json.element.village.{JsonElement, JsonName}

abstract class JsonAbstractAgent(`@context`: String,
                                 `@id`: String,
                                 id: Long,
                                 name: JsonName,
                                 image: String) extends JsonElement