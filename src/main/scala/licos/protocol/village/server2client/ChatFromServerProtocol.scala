package licos.protocol.village.server2client

import licos.json.element.village.JsonChatFromServer
import licos.protocol.PlayerChatChannel
import licos.protocol.village.part.character.SimpleCharacterProtocol
import licos.state.VillageState

final case class ChatFromServerProtocol(state:     VillageState,
                                        channel:   PlayerChatChannel,
                                        character: SimpleCharacterProtocol,
                                        isMine:    Boolean,
                                        id:        Int,
                                        counter:   Int,
                                        interval:  String,
                                        text:      String,
                                        isOver:    Boolean) {

  val json: Option[JsonChatFromServer] = {
    server2logger.ChatFromServerProtocol(state, channel, character, isMine, id, counter, interval, text, isOver, Nil).json
  }

}
