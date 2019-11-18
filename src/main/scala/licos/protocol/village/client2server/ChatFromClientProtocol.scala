package licos.protocol.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonChatFromClient
import licos.protocol.PlayerChatChannel

final case class ChatFromClientProtocol(
    village: Village,
    channel: PlayerChatChannel,
    text:    String,
    isOver:  Boolean
) {

  val json: Option[JsonChatFromClient] = {
    server2logger.ChatFromClientProtocol(village, channel, text, isOver, Nil).json
  }

}
