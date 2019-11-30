package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonChatFromClient
import licos.knowledge.{Character, Data2Knowledge, Role}
import licos.protocol.PlayerChatChannel
import play.api.libs.json.{JsValue, Json}

final case class ChatFromClientProtocol(
    village:     VillageInfo,
    channel:     PlayerChatChannel,
    text:        String,
    isOver:      Boolean,
    myCharacter: Character,
    myRole:      Role
) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonChatFromClient] = {
    server2logger.ChatFromClientProtocol(village, channel, text, isOver, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonChatFromClient =>
      Json.toJson(j)
    }
  }

}

object ChatFromClientProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(json: JsonChatFromClient, villageInfoFromLobby: VillageInfoFromLobby): Option[ChatFromClientProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val channelOpt: Option[PlayerChatChannel] =
          Data2Knowledge.playerChatChannelOpt(json.base.intensionalDisclosureRange)
        val myCharacterOpt: Option[Character] =
          Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
        val myRoleOpt: Option[Role] = village.cast.parse(json.myCharacter.name.en)
        if (channelOpt.nonEmpty && myCharacterOpt.nonEmpty && myRoleOpt.nonEmpty) {
          Some(
            ChatFromClientProtocol(
              village,
              channelOpt.get,
              json.text.`@value`,
              json.isOver,
              myCharacterOpt.get,
              myRoleOpt.get
            )
          )
        } else {
          None
        }
      case None => None
    }
  }

}
