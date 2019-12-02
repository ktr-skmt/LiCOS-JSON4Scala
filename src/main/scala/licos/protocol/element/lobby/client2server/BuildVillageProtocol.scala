package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.{JsonHostPlayer, JsonPlayerSetting, JsonRoleSetting}
import licos.json.element.lobby.client2server.JsonBuildVillage
import licos.knowledge.{AvatarSetting, Data2Knowledge}
import licos.protocol.element.lobby.part.{HostPlayerProtocol, PlayerSettingProtocol, RoleSettingProtocol}
import play.api.libs.json.{JsValue, Json}

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class BuildVillageProtocol(
    token:          UUID,
    name:           String,
    id:             Long,
    idForSearching: Int,
    hostPlayer:     HostPlayerProtocol,
    playerSetting:  PlayerSettingProtocol,
    roleSetting:    RoleSettingProtocol,
    avatar:         AvatarSetting,
    comment:        Option[String]
) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonBuildVillage] = {

    val jsonHostPlayer:    Option[JsonHostPlayer]    = hostPlayer.json
    val jsonPlayerSetting: Option[JsonPlayerSetting] = playerSetting.json
    val jsonRoleSetting:   Option[JsonRoleSetting]   = roleSetting.json

    if (jsonHostPlayer.nonEmpty && jsonPlayerSetting.nonEmpty && jsonRoleSetting.nonEmpty) {

      Some(
        new JsonBuildVillage(
          token.toString,
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

    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonBuildVillage =>
      Json.toJson(j)
    }
  }

}

object BuildVillageProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(json: JsonBuildVillage): Option[BuildVillageProtocol] = {

    val hostPlayer:    Option[HostPlayerProtocol]    = HostPlayerProtocol.read(json.hostPlayer)
    val playerSetting: Option[PlayerSettingProtocol] = PlayerSettingProtocol.read(json.playerSetting)
    val roleSetting:   Option[RoleSettingProtocol]   = RoleSettingProtocol.read(json.roleSetting)
    val avatar:        Option[AvatarSetting]         = Data2Knowledge.avatarSettingOpt(json.avatar)

    if (hostPlayer.nonEmpty && playerSetting.nonEmpty && roleSetting.nonEmpty && avatar.nonEmpty) {
      Some(
        BuildVillageProtocol(
          UUID.fromString(json.token),
          json.name,
          json.id,
          json.idForSearching,
          hostPlayer.get,
          playerSetting.get,
          roleSetting.get,
          avatar.get,
          json.comment
        )
      )
    } else {
      None
    }
  }

}
