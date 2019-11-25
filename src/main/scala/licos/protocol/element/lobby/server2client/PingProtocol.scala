package licos.protocol.element.lobby.server2client

import java.util.UUID

import licos.json.element.lobby.server2client.{JsonPing, JsonPingResult}
import licos.protocol.element.lobby.part.PingResultProtocol
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class PingProtocol(id: UUID, results: Seq[PingResultProtocol]) extends Server2ClientLobbyMessageProtocol {

  private val json: Option[JsonPing] = {

    val buffer = ListBuffer.empty[JsonPingResult]

    results foreach { result: PingResultProtocol =>
      result.json foreach { jsonPingResult: JsonPingResult =>
        buffer += jsonPingResult
      }
    }

    Some(
      new JsonPing(
        id.toString,
        buffer.result
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonPing =>
      Json.toJson(j)
    }
  }

}

object PingProtocol {

  def read(json: JsonPing): Option[PingProtocol] = {

    val buffer = ListBuffer.empty[PingResultProtocol]

    json.results foreach { result: JsonPingResult =>
      PingResultProtocol.read(result) foreach { protocol: PingResultProtocol =>
        buffer += protocol
      }
    }

    Some(
      PingProtocol(
        UUID.fromString(json.id),
        buffer.result
      )
    )
  }

}
