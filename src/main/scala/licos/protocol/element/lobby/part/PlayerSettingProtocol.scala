package licos.protocol.element.lobby.part

import licos.json.element.lobby.JsonPlayerSetting

final case class PlayerSettingProtocol(number: Int, current: Int, robot: RobotProtocol, human: HumanProtocol) {

  val json: Option[JsonPlayerSetting] = {
    for {
      jsonRobot <- robot.json
      jsonHuman <- human.json
    } yield {
      JsonPlayerSetting(
        number,
        current,
        jsonRobot,
        jsonHuman
      )
    }
  }
}

object PlayerSettingProtocol {

  def read(json: JsonPlayerSetting): Option[PlayerSettingProtocol] = {
    for {
      robot <- RobotProtocol.read(json.robot)
      human <- HumanProtocol.read(json.human)
    } yield {
      PlayerSettingProtocol(
        json.number,
        json.current,
        robot,
        human
      )
    }
  }
}
