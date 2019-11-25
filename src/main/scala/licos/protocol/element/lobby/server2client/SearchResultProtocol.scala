package licos.protocol.element.lobby.server2client

import licos.json.element.lobby.{JsonSearchResult, JsonVillage}
import licos.protocol.element.lobby.part.{ErrorProtocol, VillageProtocol}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class SearchResultProtocol(villages: Seq[VillageProtocol], error: Option[ErrorProtocol])
    extends Server2ClientLobbyMessageProtocol {

  private val json: Option[JsonSearchResult] = {

    val villageBuffer = ListBuffer.empty[JsonVillage]

    villages foreach { village: VillageProtocol =>
      village.json foreach { jsonVillage: JsonVillage =>
        villageBuffer += jsonVillage
      }
    }

    Some(
      new JsonSearchResult(
        villageBuffer.result,
        error.flatMap(_.json)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonSearchResult =>
      Json.toJson(j)
    }
  }
}

object SearchResultProtocol {

  def read(json: JsonSearchResult): Option[SearchResultProtocol] = {

    val villageBuffer = ListBuffer.empty[VillageProtocol]
    json.villages foreach { jsonVillage: JsonVillage =>
      VillageProtocol.read(jsonVillage) foreach { village: VillageProtocol =>
        villageBuffer += village
      }
    }

    Some(
      SearchResultProtocol(
        villageBuffer.result,
        json.error.flatMap(ErrorProtocol.read)
      )
    )
  }

}
