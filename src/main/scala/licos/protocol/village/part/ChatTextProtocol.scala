package licos.protocol.village.part

import java.util.Locale

import licos.json.element.village.JsonChatText

final case class ChatTextProtocol(`@value`: String, `@language`: Locale) {
  val json: JsonChatText = JsonChatText(`@value`, `@language`.getLanguage)
}
