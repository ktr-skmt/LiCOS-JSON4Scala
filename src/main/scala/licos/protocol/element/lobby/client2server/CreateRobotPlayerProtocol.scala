package licos.protocol.element.lobby.client2server

import java.net.URL
import java.util.Locale

import licos.json.element.lobby.client2server.{JsonCreateRobotPlayer, JsonSupport}
import licos.protocol.element.lobby.part.{SupportProtocol, SupportedCompositionProtocol}
import play.api.libs.json.{JsValue, Json}

final case class CreateRobotPlayerProtocol(
    name:             String,
    image:            URL,
    language:         Locale,
    isFullyAutomated: Boolean,
    support:          SupportProtocol
) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonCreateRobotPlayer] = {
    support.json.map { jsonSupport: JsonSupport =>
      new JsonCreateRobotPlayer(
        name,
        image.toString,
        language.getLanguage,
        isFullyAutomated,
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
    {
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
    }.map { support =>
      CreateRobotPlayerProtocol(
        json.name,
        new URL(json.image),
        new Locale(json.language),
        json.isFullyAutomated,
        support
      )
    }
  }

}
