package licos.protocol.element.lobby.server2client

import licos.json.element.lobby.server2client.{JsonPlayerInWaitingPage, JsonWaitingPage}
import licos.json.element.lobby.JsonVillage
import licos.protocol.element.lobby.part.{ErrorProtocol, PlayerInWaitingPageProtocol, VillageProtocol}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.MutableDataStructures", "org.wartremover.warts.OptionPartial"))
final case class WaitingPageProtocol(
    village: VillageProtocol,
    players: Seq[PlayerInWaitingPageProtocol],
    error:   Option[ErrorProtocol]
) extends Server2ClientLobbyMessageProtocol {

  private val json: Option[JsonWaitingPage] = {

    val jsonVillage: Option[JsonVillage] = village.json

    val playerBuffer = ListBuffer.empty[JsonPlayerInWaitingPage]
    players foreach { player: PlayerInWaitingPageProtocol =>
      player.json foreach { jsonPlayerInWaitingPage: JsonPlayerInWaitingPage =>
        playerBuffer += jsonPlayerInWaitingPage
      }
    }

    if (jsonVillage.nonEmpty) {
      Some(
        new JsonWaitingPage(
          jsonVillage.get,
          playerBuffer.result,
          error.flatMap(_.json)
        )
      )
    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonWaitingPage =>
      Json.toJson(j)
    }
  }
}

object WaitingPageProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.MutableDataStructures", "org.wartremover.warts.OptionPartial"))
  def read(json: JsonWaitingPage): Option[WaitingPageProtocol] = {

    val village: Option[VillageProtocol] = VillageProtocol.read(json.village)
    val playerBuffer = ListBuffer.empty[PlayerInWaitingPageProtocol]
    json.players foreach { jsonPlayer: JsonPlayerInWaitingPage =>
      PlayerInWaitingPageProtocol.read(jsonPlayer) foreach { player: PlayerInWaitingPageProtocol =>
        playerBuffer += player
      }
    }

    if (village.nonEmpty) {
      Some(
        WaitingPageProtocol(
          village.get,
          playerBuffer.result,
          json.error.flatMap(ErrorProtocol.read)
        )
      )
    } else {
      None
    }
  }

}
