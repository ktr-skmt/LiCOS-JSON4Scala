package licos.knowledge

sealed class Outcome(val label: String) extends Serializable {
  override def toString: String = label
}

case object Victory extends Outcome("win")
case object Defeat extends Outcome("lose")
