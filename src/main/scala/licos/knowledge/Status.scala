package licos.knowledge

object Status {
  val defaultValue: Status = Alive(updateDay = 1, updatePhase = Morning)

  def labelToStatusOpt(label: String, day: Int, phase: Phase): Option[Status] = {
    label match {
      case "alive"              => Option(Alive(day, phase))
      case "dead"               => Option(Dead(day, phase))
      case "death by execution" => Option(DeathByExecution(day, phase))
      case "death by attack"    => Option(DeathByAttack(day, phase))
      case "death by fear"      => Option(DeathByFear(day, phase))
      case "unnatural death"    => Option(UnnaturalDeath(day, phase))
      case _                    => None
    }
  }
}

sealed abstract class Status(val label: String, val updateDay: Int, val updatePhase: Phase) {

  override def toString: String = label

  def toIntermediateStatus: Status
  def isAlive: Boolean = {
    this match {
      case Alive(_, _) =>
        true
      case _ =>
        false
    }
  }
  def isDead: Boolean = !isAlive

  override def equals(o: Any): Boolean = {
    o match {
      case status: Status if status.label == this.label => true
      case _ => false
    }
  }
}

final case class Alive(override val updateDay: Int, override val updatePhase: Phase)
    extends Status("alive", updateDay, updatePhase) {
  override def toIntermediateStatus: Status = this
}

final case class Dead(override val updateDay: Int, override val updatePhase: Phase)
    extends Status("dead", updateDay, updatePhase) {
  override def toIntermediateStatus: Status = this
}

final case class DeathByExecution(override val updateDay: Int, override val updatePhase: Phase)
    extends Status("death by execution", updateDay, updatePhase) {
  override def toIntermediateStatus: Status = DeathByExecution(updateDay, updatePhase)
}

final case class DeathByAttack(override val updateDay: Int, override val updatePhase: Phase)
    extends Status("death by attack", updateDay, updatePhase) {
  override def toIntermediateStatus: Status = Dead(updateDay, updatePhase)
}

final case class DeathByFear(override val updateDay: Int, override val updatePhase: Phase)
    extends Status("death by fear", updateDay, updatePhase) {
  override def toIntermediateStatus: Status = Dead(updateDay, updatePhase)
}

final case class UnnaturalDeath(override val updateDay: Int, override val updatePhase: Phase)
    extends Status("unnatural death", updateDay, updatePhase) {
  override def toIntermediateStatus: Status = UnnaturalDeath(updateDay, updatePhase)
}
