package licos.protocol.element.lobby.server2client

import licos.json.element.lobby.JsonVillage
import licos.json.element.lobby.server2client.JsonLobby
import licos.knowledge.{Data2Knowledge, Lobby}
import licos.protocol.element.lobby.part.{ErrorProtocol, VillageProtocol}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class LobbyProtocol(lobby: Lobby, villages: Seq[VillageProtocol], error: Option[ErrorProtocol])
    extends Server2ClientLobbyMessageProtocol {

  private val json: Option[JsonLobby] = {
    val villageBuffer = ListBuffer.empty[JsonVillage]
    villages foreach { village: VillageProtocol =>
      village.json foreach { jsonVillage: JsonVillage =>
        villageBuffer += jsonVillage
      }
    }

    Some(
      new JsonLobby(
        lobby.label,
        villageBuffer.result,
        error.flatMap(_.json)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonLobby =>
      Json.toJson(j)
    }
  }

}

object LobbyProtocol {

  def read(json: JsonLobby): Option[LobbyProtocol] = {

    val lobbyOpt = Data2Knowledge.lobbyOpt(json.lobby)

    val villageBuffer = ListBuffer.empty[VillageProtocol]
    json.villages foreach { village: JsonVillage =>
      VillageProtocol.read(village) foreach { village: VillageProtocol =>
        villageBuffer += village
      }
    }

    if (lobbyOpt.nonEmpty) {
      Some(
        LobbyProtocol(
          lobbyOpt.get,
          villageBuffer.result,
          json.error.flatMap(ErrorProtocol.read)
        )
      )
    } else {
      None
    }
  }

}
