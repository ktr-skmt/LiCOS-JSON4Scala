package licos.knowledge

sealed abstract class Outcome(val label: String) extends Product with Serializable {
  override def toString: String = label
}

case object Victory extends Outcome("win")
case object Defeat extends Outcome("lose")
