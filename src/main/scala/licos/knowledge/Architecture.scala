package licos.knowledge

sealed abstract class Architecture(val label: String) {
  def isHuman: Boolean = this == HumanArchitecture

  override def toString: String = s"Architecture($label)"
}

case object HumanArchitecture extends Architecture("human")
case object JohnVonNeumannArchitecture extends Architecture("Neumann")
