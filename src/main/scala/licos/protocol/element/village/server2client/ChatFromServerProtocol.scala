package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonChatFromServer
import licos.protocol.{GraveChannel, PlayerChatChannel, PrivateChannel, PublicChannel, WerewolfChannel}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.SimpleCharacterProtocol

final case class ChatFromServerProtocol(
    village:   Village,
    channel:   PlayerChatChannel,
    character: SimpleCharacterProtocol,
    isMine:    Boolean,
    id:        Int,
    counter:   Int,
    interval:  Int,
    text:      String,
    isOver:    Boolean
) extends VillageMessageProtocol {

  val json: Option[JsonChatFromServer] = {
    server2logger
      .ChatFromServerProtocol(village, channel, character, isMine, id, counter, interval, text, isOver, Nil)
      .json
  }

}

object ChatFromServerProtocol {

  def read(json: JsonChatFromServer, village: Village): Option[ChatFromServerProtocol] = {

    val channelOpt: Option[PlayerChatChannel] = {
      json.base.intensionalDisclosureRange match {
        case PublicChannel.channel.label => Option(PublicChannel)
        case PrivateChannel.channel.label => Option(PrivateChannel)
        case WerewolfChannel.channel.label => Option(WerewolfChannel)
        case GraveChannel.channel.label => Option(GraveChannel)
        case _ => None
      }
    }

    if (channelOpt.nonEmpty && village.myCharacterOpt.nonEmpty && village.myRoleOpt.nonEmpty) {
      Some(ChatFromServerProtocol(
        village,
        channelOpt.get,
        SimpleCharacterProtocol(
          village.myCharacterOpt.get,
          village.id,
          village.language,
          village.myRoleOpt.get
        ),
        json.isMine,
        json.id,
        json.counter,
        json.interval,
        json.text.`@value`,
        json.isOver
      ))
    } else {
      None
    }
  }

}
