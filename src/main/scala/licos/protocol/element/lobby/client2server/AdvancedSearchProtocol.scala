package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonAdvancedSearch
import licos.knowledge.{AvatarSetting, Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class AdvancedSearchProtocol(
    token:         UUID,
    lobby:         Lobby,
    villageName:   Option[String],
    hostName:      Option[String],
    minimum:       Option[Int],
    maximum:       Option[Int],
    avatarSetting: Option[AvatarSetting],
    comment:       Option[String]
) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonAdvancedSearch] = {
    Some(
      JsonAdvancedSearch(
        JsonAdvancedSearch.`type`,
        token.toString,
        lobby.label,
        villageName,
        hostName,
        minimum,
        maximum,
        avatarSetting.map(_.label),
        comment
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object AdvancedSearchProtocol {

  def read(json: JsonAdvancedSearch): Option[AdvancedSearchProtocol] = {
    Data2Knowledge.lobbyOpt(json.lobby).map { lobby: Lobby =>
      AdvancedSearchProtocol(
        UUID.fromString(json.token),
        lobby,
        json.villageName,
        json.hostName,
        json.minimum,
        json.maximum,
        json.avatar.flatMap(Data2Knowledge.avatarSettingOpt),
        json.comment
      )
    }
  }
}
