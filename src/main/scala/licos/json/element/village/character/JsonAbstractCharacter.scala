package licos.json.element.village.character

import licos.json.element.village.{JsonElement, JsonName}

abstract class JsonAbstractCharacter(`@context`: String, `@id`: String, id: Int, name: JsonName, image: String)
    extends JsonElement
