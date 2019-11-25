package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.JsonAdvancedSearch
import licos.knowledge.{AvatarSetting, Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class AdvancedSearchProtocol(
    token:         UUID,
    lobby:         Lobby,
    villageName:   Option[String],
    hostName:      Option[String],
    minimum:       Option[Int],
    maximum:       Option[Int],
    avatarSetting: AvatarSetting,
    comment:       Option[String]
) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonAdvancedSearch] = {
    Some(
      JsonAdvancedSearch(
        JsonAdvancedSearch.`type`,
        token.toString,
        lobby.label,
        villageName,
        hostName,
        minimum,
        maximum,
        avatarSetting.label,
        comment
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonAdvancedSearch =>
      Json.toJson(j)
    }
  }
}

object AdvancedSearchProtocol {

  def read(json: JsonAdvancedSearch): Option[AdvancedSearchProtocol] = {
    val lobbyOpt:         Option[Lobby]         = Data2Knowledge.lobbyOpt(json.lobby)
    val avatarSettingOpt: Option[AvatarSetting] = Data2Knowledge.avatarSettingOpt(json.avatar)
    if (lobbyOpt.nonEmpty && avatarSettingOpt.nonEmpty) {
      Some(
        AdvancedSearchProtocol(
          UUID.fromString(json.token),
          lobbyOpt.get,
          json.villageName,
          json.hostName,
          json.minimum,
          json.maximum,
          avatarSettingOpt.get,
          json.comment
        )
      )
    } else {
      None
    }
  }

}
