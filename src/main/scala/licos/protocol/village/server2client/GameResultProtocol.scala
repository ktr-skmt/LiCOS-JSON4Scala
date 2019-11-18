package licos.protocol.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonGameResult
import licos.protocol.village.part.character.ResultCharacterProtocol
import licos.protocol.village.part.role.ResultRoleProtocol

final case class GameResultProtocol(
    village:   Village,
    character: Seq[ResultCharacterProtocol],
    role:      Seq[ResultRoleProtocol]
) {

  val json: Option[JsonGameResult] = {
    server2logger.GameResultProtocol(village, character, role, Nil).json
  }

}
