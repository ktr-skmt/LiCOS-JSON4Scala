package licos.protocol.element.lobby.server2client

import licos.json.element.lobby.JsonVillage
import licos.json.element.lobby.server2client.JsonWaitingPage
import licos.protocol.element.lobby.part.{ErrorProtocol, PlayerInWaitingPageProtocol, VillageProtocol}
import play.api.libs.json.{JsValue, Json}

final case class WaitingPageProtocol(
    village: VillageProtocol,
    players: Seq[PlayerInWaitingPageProtocol],
    error:   Option[ErrorProtocol]
) extends Server2ClientLobbyMessageProtocol {

  private val json: Option[JsonWaitingPage] = {
    village.json.map { jsonVillage: JsonVillage =>
      new JsonWaitingPage(
        jsonVillage,
        players.flatMap(_.json.toList),
        error.flatMap(_.json)
      )
    }
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object WaitingPageProtocol {

  def read(json: JsonWaitingPage): Option[WaitingPageProtocol] = {
    VillageProtocol.read(json.village).map { village: VillageProtocol =>
      WaitingPageProtocol(
        village,
        json.players.flatMap(j => PlayerInWaitingPageProtocol.read(j).toList),
        json.error.flatMap(ErrorProtocol.read)
      )
    }
  }

}
