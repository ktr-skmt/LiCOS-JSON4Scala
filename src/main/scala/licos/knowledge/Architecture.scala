package licos.knowledge

sealed abstract class Architecture(val label: String) {
  def isHuman: Boolean = this == HumanArchitecture
  def isRobot: Boolean = !isHuman

  override def toString: String = s"Architecture($label)"
}

case object HumanArchitecture extends Architecture("human")
case object AutomatedRobotArchitecture extends Architecture("automated robot")
case object SemiAutomatedRobotArchitecture extends Architecture("semi-automated robot")
