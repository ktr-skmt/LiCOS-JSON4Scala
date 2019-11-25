package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonChatFromClient
import licos.knowledge.Data2Knowledge
import licos.protocol.PlayerChatChannel
import play.api.libs.json.{JsValue, Json}

final case class ChatFromClientProtocol(
    village: Village,
    channel: PlayerChatChannel,
    text:    String,
    isOver:  Boolean
) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonChatFromClient] = {
    server2logger.ChatFromClientProtocol(village, channel, text, isOver, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object ChatFromClientProtocol {

  def read(json: JsonChatFromClient, village: Village): Option[ChatFromClientProtocol] = {
    val channelOpt: Option[PlayerChatChannel] =
      Data2Knowledge.playerChatChannelOpt(json.base.intensionalDisclosureRange)
    if (channelOpt.nonEmpty) {
      Some(
        ChatFromClientProtocol(
          village,
          channelOpt.get,
          json.text.`@value`,
          json.isOver
        )
      )
    } else {
      None
    }
  }

}
