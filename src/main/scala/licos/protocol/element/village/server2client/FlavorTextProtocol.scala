package licos.protocol.element.village.server2client

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.server2client.{JsonChatFromServer, JsonFlavorText}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class FlavorTextProtocol(village: VillageInfo, flavorText: Seq[ChatFromServerProtocol])
    extends Server2ClientVillageMessageProtocol {

  private val json: Option[JsonFlavorText] = {
    server2logger.FlavorTextProtocol(village, flavorText, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonFlavorText =>
      Json.toJson(j)
    }
  }

}

object FlavorTextProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.MutableDataStructures"))
  def read(json: JsonFlavorText, villageInfoFromLobby: VillageInfoFromLobby): Option[FlavorTextProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val chatBuffer = ListBuffer.empty[ChatFromServerProtocol]
        json.flavorText foreach { jsonChatFromServer: JsonChatFromServer =>
          ChatFromServerProtocol.read(jsonChatFromServer, villageInfoFromLobby) foreach chatBuffer.+=
        }
        Some(
          FlavorTextProtocol(
            village,
            chatBuffer.result
          )
        )
      case None => None
    }
  }

}
