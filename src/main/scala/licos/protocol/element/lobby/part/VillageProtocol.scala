package licos.protocol.element.lobby.part

import licos.json.element.lobby.JsonVillage
import licos.knowledge.{AvatarSetting, Data2Knowledge}

final case class VillageProtocol(
    name:           String,
    id:             Long,
    idForSearching: Long,
    hostPlayer:     HostPlayerProtocol,
    playerSetting:  PlayerSettingProtocol,
    roleSetting:    RoleSettingProtocol,
    avatar:         AvatarSetting,
    comment:        Option[String]
) {

  lazy val json: Option[JsonVillage] = {
    for {
      jsonHostPlayer    <- hostPlayer.json
      jsonPlayerSetting <- playerSetting.json
      jsonRoleSetting   <- roleSetting.json
    } yield {
      JsonVillage(
        name,
        id,
        idForSearching,
        jsonHostPlayer,
        jsonPlayerSetting,
        jsonRoleSetting,
        avatar.label,
        comment
      )
    }
  }
}

object VillageProtocol {

  def read(json: JsonVillage): Option[VillageProtocol] = {
    for {
      hostPlayer    <- HostPlayerProtocol.read(json.hostPlayer)
      playerSetting <- PlayerSettingProtocol.read(json.playerSetting)
      roleSetting   <- RoleSettingProtocol.read(json.roleSetting)
      avatarSetting <- Data2Knowledge.avatarSettingOpt(json.avatar)
    } yield {
      VillageProtocol(
        json.name,
        json.id,
        json.idForSearching,
        hostPlayer,
        playerSetting,
        roleSetting,
        avatarSetting,
        json.comment
      )
    }
  }

}
