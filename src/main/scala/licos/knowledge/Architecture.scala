package licos.knowledge

sealed abstract class Architecture(val label: String) {
  def isHuman: Boolean = {
    import cats.implicits._
    this.label === HumanArchitecture.label
  }
  def isRobot: Boolean = !isHuman

  override def toString: String = s"Architecture($label)"
}

case object HumanArchitecture extends Architecture("human")
case object AutomatedRobotArchitecture extends Architecture("automated robot")
case object SemiAutomatedRobotArchitecture extends Architecture("semi-automated robot")
