package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.{JsonChatFromServer, JsonFlavorText}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class FlavorTextProtocol(village: Village, flavorText: Seq[ChatFromServerProtocol])
    extends Server2ClientVillageMessageProtocol {

  private val json: Option[JsonFlavorText] = {
    server2logger.FlavorTextProtocol(village, flavorText, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object FlavorTextProtocol {

  def read(json: JsonFlavorText, village: Village): Option[FlavorTextProtocol] = {
    val chatBuffer = ListBuffer.empty[ChatFromServerProtocol]
    json.flavorText foreach { jsonChatFromServer: JsonChatFromServer =>
      ChatFromServerProtocol.read(jsonChatFromServer, village) foreach chatBuffer.+=
    }
    Some(
      FlavorTextProtocol(
        village,
        chatBuffer.result
      )
    )
  }

}