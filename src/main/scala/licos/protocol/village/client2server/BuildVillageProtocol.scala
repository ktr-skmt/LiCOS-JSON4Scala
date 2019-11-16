package licos.protocol.village.client2server

import licos.json.element.lobby.JsonBuildVillage
import licos.state.VillageState

final case class BuildVillageProtocol(state: VillageState) {

  val json: Option[JsonBuildVillage] = {
    if (state.isAvailable) {
      Option(new JsonBuildVillage(
        state.token.toString,
        state.villageName,
        state.villageId,
        state.idForSearching,
        state.hostPlayer,
        state.playerSetting,
        state.roleSetting,
        state.avatarName,
        state.comment
      ))
    } else {
      None
    }
  }
}
