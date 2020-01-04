package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.client2server.{JsonCreateRobotPlayer, JsonSupport}
import licos.knowledge.{AutomationType, Data2Knowledge}
import licos.protocol.element.lobby.part.{SupportProtocol, SupportedCompositionProtocol}
import play.api.libs.json.{JsValue, Json}

final case class CreateRobotPlayerProtocol(name: String, automationType: AutomationType, support: SupportProtocol)
    extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonCreateRobotPlayer] = {
    support.json.map { jsonSupport: JsonSupport =>
      new JsonCreateRobotPlayer(
        name,
        automationType.label,
        jsonSupport
      )
    }
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

}

object CreateRobotPlayerProtocol {

  def read(json: JsonCreateRobotPlayer): Option[CreateRobotPlayerProtocol] = {
    for {
      automationType <- Data2Knowledge.automationTypeOpt(json.automationType)
      support <- {
        for {
          protocol4  <- SupportedCompositionProtocol.read(json.support.`4`, 4)
          protocol5  <- SupportedCompositionProtocol.read(json.support.`5`, 5)
          protocol6  <- SupportedCompositionProtocol.read(json.support.`6`, 6)
          protocol7  <- SupportedCompositionProtocol.read(json.support.`7`, 7)
          protocol8  <- SupportedCompositionProtocol.read(json.support.`8`, 8)
          protocol9  <- SupportedCompositionProtocol.read(json.support.`9`, 9)
          protocol10 <- SupportedCompositionProtocol.read(json.support.`10`, 10)
          protocol11 <- SupportedCompositionProtocol.read(json.support.`11`, 11)
          protocol12 <- SupportedCompositionProtocol.read(json.support.`12`, 12)
          protocol13 <- SupportedCompositionProtocol.read(json.support.`13`, 13)
          protocol14 <- SupportedCompositionProtocol.read(json.support.`14`, 14)
          protocol15 <- SupportedCompositionProtocol.read(json.support.`15`, 15)
        } yield {
          SupportProtocol(
            protocol4,
            protocol5,
            protocol6,
            protocol7,
            protocol8,
            protocol9,
            protocol10,
            protocol11,
            protocol12,
            protocol13,
            protocol14,
            protocol15
          )
        }
      }
    } yield {
      CreateRobotPlayerProtocol(
        json.name,
        automationType,
        support
      )
    }
  }

}
