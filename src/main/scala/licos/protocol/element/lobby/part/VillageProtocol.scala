package licos.protocol.element.lobby.part

import licos.json.element.lobby.{JsonHostPlayer, JsonPlayerSetting, JsonRoleSetting, JsonVillage}
import licos.knowledge.{AvatarSetting, Data2Knowledge}

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class VillageProtocol(
    name:           String,
    id:             Long,
    idForSearching: Int,
    hostPlayer:     HostPlayerProtocol,
    playerSetting:  PlayerSettingProtocol,
    roleSetting:    RoleSettingProtocol,
    avatar:         AvatarSetting,
    comment:        Option[String]
) {

  val json: Option[JsonVillage] = {

    val jsonHostPlayer:    Option[JsonHostPlayer]    = hostPlayer.json
    val jsonPlayerSetting: Option[JsonPlayerSetting] = playerSetting.json
    val jsonRoleSetting:   Option[JsonRoleSetting]   = roleSetting.json

    Some(
      JsonVillage(
        name,
        id,
        idForSearching,
        jsonHostPlayer.get,
        jsonPlayerSetting.get,
        jsonRoleSetting.get,
        avatar.label,
        comment
      )
    )
  }

}

object VillageProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(json: JsonVillage): Option[VillageProtocol] = {

    val hostPlayer:    Option[HostPlayerProtocol]    = HostPlayerProtocol.read(json.hostPlayer)
    val playerSetting: Option[PlayerSettingProtocol] = PlayerSettingProtocol.read(json.playerSetting)
    val roleSetting:   Option[RoleSettingProtocol]   = RoleSettingProtocol.read(json.roleSetting)
    val avatarSetting: Option[AvatarSetting]         = Data2Knowledge.avatarSettingOpt(json.avatar)

    if (hostPlayer.nonEmpty && playerSetting.nonEmpty && roleSetting.nonEmpty && avatarSetting.nonEmpty) {

      Some(
        VillageProtocol(
          json.name,
          json.id,
          json.idForSearching,
          hostPlayer.get,
          playerSetting.get,
          roleSetting.get,
          avatarSetting.get,
          json.comment
        )
      )

    } else {
      None
    }
  }

}
