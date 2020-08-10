package licos.knowledge

sealed abstract class Status(val label: String) extends Product with Serializable {

  override def toString: String = label

  def toIntermediateStatus: Status
  def isAlive: Boolean = {
    import cats.implicits._
    this.label === Alive.label
  }
  def isDead: Boolean = !isAlive

  override def equals(o: Any): Boolean = {
    import cats.implicits._
    o match {
      case status: Status if status.label === this.label => true
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
