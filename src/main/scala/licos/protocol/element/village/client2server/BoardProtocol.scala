package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonBoard
import licos.knowledge.{Character, PolarityMark, Role}
import licos.protocol.element.village.VillageMessageProtocol

final case class BoardProtocol(village: Village, character: Character, role: Role, prediction: PolarityMark) extends VillageMessageProtocol {

  val json: Option[JsonBoard] = {
    server2logger.BoardProtocol(village, character, role, prediction, Nil).json
  }

}

object BoardProtocol {

  def read(json: JsonBoard, village: Village): Option[BoardProtocol] = {
    Some(
      BoardProtocol(
        village,

      )
    )
  }

}