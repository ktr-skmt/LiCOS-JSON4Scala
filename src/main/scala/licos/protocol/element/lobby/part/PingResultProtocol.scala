package licos.protocol.element.lobby.part

import java.util.UUID

import licos.json.element.lobby.server2client.JsonPingResult
import licos.knowledge.{Data2Knowledge, Status}

final case class PingResultProtocol(token: UUID, ping: String, status: Status) {

  val json: Option[JsonPingResult] = {
    Some(
      JsonPingResult(
        token.toString,
        ping,
        status.label
      )
    )
  }

}

object PingResultProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(json: JsonPingResult): Option[PingResultProtocol] = {

    val status: Option[Status] = Data2Knowledge.statusOpt(json.status)

    if (status.nonEmpty) {
      Some(
        PingResultProtocol(
          UUID.fromString(json.token),
          json.ping,
          status.get
        )
      )
    } else {
      None
    }
  }

}
