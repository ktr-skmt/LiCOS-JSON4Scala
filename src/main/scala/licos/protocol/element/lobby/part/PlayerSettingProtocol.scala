package licos.protocol.element.lobby.part

import licos.json.element.lobby.{JsonHuman, JsonPlayerSetting, JsonRobot}

final case class PlayerSettingProtocol(number: Int, current: Int, robot: RobotProtocol, human: HumanProtocol) {

  val json: Option[JsonPlayerSetting] = {

    val jsonRobot: Option[JsonRobot] = robot.json
    val jsonHuman: Option[JsonHuman] = human.json

    if (jsonRobot.nonEmpty && jsonHuman.nonEmpty) {
      Some(
        JsonPlayerSetting(
          number,
          current,
          jsonRobot.get,
          jsonHuman.get
        )
      )
    } else {
      None
    }
  }

}

object PlayerSettingProtocol {

  def read(json: JsonPlayerSetting): Option[PlayerSettingProtocol] = {

    val robotOpt: Option[RobotProtocol] = RobotProtocol.read(json.robot)
    val humanOpt: Option[HumanProtocol] = HumanProtocol.read(json.human)

    if (robotOpt.nonEmpty && humanOpt.nonEmpty) {
      Some(
        PlayerSettingProtocol(
          json.number,
          json.current,
          robotOpt.get,
          humanOpt.get
        )
      )
    } else {
      None
    }
  }

}
