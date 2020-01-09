package licos.protocol.element.lobby.part

import licos.json.element.lobby.client2server.JsonSupport

final case class SupportProtocol(
    `4`:  SupportedCompositionProtocol,
    `5`:  SupportedCompositionProtocol,
    `6`:  SupportedCompositionProtocol,
    `7`:  SupportedCompositionProtocol,
    `8`:  SupportedCompositionProtocol,
    `9`:  SupportedCompositionProtocol,
    `10`: SupportedCompositionProtocol,
    `11`: SupportedCompositionProtocol,
    `12`: SupportedCompositionProtocol,
    `13`: SupportedCompositionProtocol,
    `14`: SupportedCompositionProtocol,
    `15`: SupportedCompositionProtocol
) {

  lazy val json: Option[JsonSupport] = {
    for {
      json4  <- `4`.json
      json5  <- `5`.json
      json6  <- `6`.json
      json7  <- `7`.json
      json8  <- `8`.json
      json9  <- `9`.json
      json10 <- `10`.json
      json11 <- `11`.json
      json12 <- `12`.json
      json13 <- `13`.json
      json14 <- `14`.json
      json15 <- `15`.json
    } yield {
      JsonSupport(
        json4,
        json5,
        json6,
        json7,
        json8,
        json9,
        json10,
        json11,
        json12,
        json13,
        json14,
        json15
      )
    }
  }
}

object SupportProtocol {

  def read(json: JsonSupport): Option[SupportProtocol] = {
    for {
      protocol4  <- SupportedCompositionProtocol.read(json.`4`, 4)
      protocol5  <- SupportedCompositionProtocol.read(json.`5`, 5)
      protocol6  <- SupportedCompositionProtocol.read(json.`6`, 6)
      protocol7  <- SupportedCompositionProtocol.read(json.`7`, 7)
      protocol8  <- SupportedCompositionProtocol.read(json.`8`, 8)
      protocol9  <- SupportedCompositionProtocol.read(json.`9`, 9)
      protocol10 <- SupportedCompositionProtocol.read(json.`10`, 10)
      protocol11 <- SupportedCompositionProtocol.read(json.`11`, 11)
      protocol12 <- SupportedCompositionProtocol.read(json.`12`, 12)
      protocol13 <- SupportedCompositionProtocol.read(json.`13`, 13)
      protocol14 <- SupportedCompositionProtocol.read(json.`14`, 14)
      protocol15 <- SupportedCompositionProtocol.read(json.`15`, 15)
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

}
