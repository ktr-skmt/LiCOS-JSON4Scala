package licos.knowledge

sealed abstract class AutomationType(val label: String) {
  def playerType: Architecture
}

case object FullyAutomated extends AutomationType("fully automated") {
  override def playerType: Architecture = FullyAutomatedRobotArchitecture
}

case object SemiAutomated extends AutomationType("semi-automated") {
  override def playerType: Architecture = SemiAutomatedRobotArchitecture
}
