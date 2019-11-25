package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.client2server.JsonVote
import licos.knowledge.{Character, Data2Knowledge}
import play.api.libs.json.{JsValue, Json}

final case class VoteProtocol(village: Village, character: Character) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonVote] = {
    server2logger.VoteProtocol(village, character, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonVote =>
      Json.toJson(j)
    }
  }

}

object VoteProtocol {

  def read(json: JsonVote, village: Village): Option[VoteProtocol] = {
    val characterOpt: Option[Character] = Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
    if (characterOpt.nonEmpty) {
      Some(
        VoteProtocol(
          village,
          characterOpt.get
        )
      )
    } else {
      None
    }
  }

}
