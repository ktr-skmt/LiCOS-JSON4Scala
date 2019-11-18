package licos.protocol.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonChatFromServer
import licos.protocol.PlayerChatChannel
import licos.protocol.village.part.character.SimpleCharacterProtocol

final case class ChatFromServerProtocol(
    village:   Village,
    channel:   PlayerChatChannel,
    character: SimpleCharacterProtocol,
    isMine:    Boolean,
    id:        Int,
    counter:   Int,
    interval:  String,
    text:      String,
    isOver:    Boolean
) {

  val json: Option[JsonChatFromServer] = {
    server2logger
      .ChatFromServerProtocol(village, channel, character, isMine, id, counter, interval, text, isOver, Nil)
      .json
  }

}
