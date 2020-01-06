package licos.protocol.element.lobby.part

import licos.json.element.lobby.JsonRobot

final case class RobotProtocol(min: Int, current: Int) {

  lazy val json: Option[JsonRobot] = {
    Some(
      JsonRobot(
        min,
        current
      )
    )
  }

}

object RobotProtocol {

  def read(json: JsonRobot): Option[RobotProtocol] = {
    Some(
      RobotProtocol(
        json.min,
        json.current
      )
    )
  }

}
