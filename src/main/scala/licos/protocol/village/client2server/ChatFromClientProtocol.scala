package licos.protocol.village.client2server

import licos.json.element.village.JsonChatFromClient
import licos.protocol.PlayerChatChannel
import licos.state.VillageState

final case class ChatFromClientProtocol(state:   VillageState,
                                        channel: PlayerChatChannel,
                                        text:    String,
                                        isOver:  Boolean) {

  val json: Option[JsonChatFromClient] = {
    server2logger.ChatFromClientProtocol(state, channel, text, isOver, Nil).json
  }

}
