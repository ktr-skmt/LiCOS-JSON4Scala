package licos.protocol.village.server2client

import licos.json.element.village.JsonGameResult
import licos.protocol.village.part.character.ResultCharacterProtocol
import licos.protocol.village.part.role.ResultRoleProtocol
import licos.state.VillageState

final case class GameResultProtocol(state:     VillageState,
                                    character: Seq[ResultCharacterProtocol],
                                    role:      Seq[ResultRoleProtocol]) {

  val json: Option[JsonGameResult] = {
    server2logger.GameResultProtocol(state, character, role, Nil).json
  }

}
