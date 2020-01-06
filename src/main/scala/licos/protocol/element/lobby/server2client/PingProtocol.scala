package licos.protocol.element.lobby.server2client

import java.util.UUID

import licos.json.element.lobby.server2client.JsonPing
import licos.protocol.element.lobby.part.PingResultProtocol
import play.api.libs.json.{JsValue, Json}

final case class PingProtocol(id: UUID, results: Seq[PingResultProtocol]) extends Server2ClientLobbyMessageProtocol {

  private lazy val json: Option[JsonPing] = {
    Some(
      new JsonPing(
        id.toString,
        results.flatMap(_.json.toList)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object PingProtocol {

  def read(json: JsonPing): Option[PingProtocol] = {
    Some(
      PingProtocol(
        UUID.fromString(json.id),
        json.results.flatMap(j => PingResultProtocol.read(j).toList)
      )
    )
  }

}
