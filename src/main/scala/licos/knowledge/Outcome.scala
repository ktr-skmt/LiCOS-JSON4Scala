package licos.knowledge

sealed class Outcome(val label: String) {
  override def toString: String = label
}

case object Victory extends Outcome("win")
case object Defeat extends Outcome("lose")
