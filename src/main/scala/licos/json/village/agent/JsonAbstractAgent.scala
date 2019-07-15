package licos.json.village.agent

import licos.json.village.{JsonElement, JsonName}

abstract class JsonAbstractAgent(`@context`: String,
                                 `@id`: String,
                                 id: Long,
                                 name: JsonName,
                                 image: String) extends JsonElement