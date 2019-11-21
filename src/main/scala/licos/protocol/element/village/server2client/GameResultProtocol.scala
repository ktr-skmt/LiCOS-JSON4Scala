package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonGameResult
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.ResultCharacterProtocol
import licos.protocol.element.village.part.role.ResultRoleProtocol

final case class GameResultProtocol(
    village:   Village,
    character: Seq[ResultCharacterProtocol],
    role:      Seq[ResultRoleProtocol]
) extends VillageMessageProtocol {

  val json: Option[JsonGameResult] = {
    server2logger.GameResultProtocol(village, character, role, Nil).json
  }

}

object GameResultProtocol {

  def read(json: JsonGameResult, village: Village): Option[GameResultProtocol] = {

  }

}