package licos.protocol.element.lobby.part

import licos.json.element.lobby.JsonHuman

final case class HumanProtocol(max: Int, current: Int) {

  lazy val json: Option[JsonHuman] = {
    Some(
      JsonHuman(
        max,
        current
      )
    )
  }

}

object HumanProtocol {

  def read(json: JsonHuman): Option[HumanProtocol] = {
    Some(
      HumanProtocol(
        json.max,
        json.current
      )
    )
  }

}
