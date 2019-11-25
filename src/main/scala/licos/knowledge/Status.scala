package licos.knowledge

sealed abstract class Status(val label: String) {

  override def toString: String = label

  def toIntermediateStatus: Status
  def isAlive: Boolean = this == Alive
  def isDead:  Boolean = !isAlive

  override def equals(o: Any): Boolean = {
    o match {
      case status: Status if status.label == this.label => true
      case _ => false
    }
  }
}

case object Alive extends Status("alive") {
  override def toIntermediateStatus: Status = this
}

case object Dead extends Status("dead") {
  override def toIntermediateStatus: Status = this
}

case object DeathByExecution extends Status("death by execution") {
  override def toIntermediateStatus: Status = this
}

case object DeathByAttack extends Status("death by attack") {
  override def toIntermediateStatus: Status = Dead
}

case object DeathByFear extends Status("death by fear") {
  override def toIntermediateStatus: Status = Dead
}

case object UnnaturalDeath extends Status("unnatural death") {
  override def toIntermediateStatus: Status = UnnaturalDeath
}
