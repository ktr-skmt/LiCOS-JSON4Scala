package licos.entity

import licos.knowledge.{Alive, Character, Morning, Night, Noon, Outcome, Phase, Role, Status}

sealed abstract class AvatarInVillage() extends Product with Serializable

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
final case class PlayerInVillage(character: Character, role: Role) extends AvatarInVillage() {

  private var outcomeOpt:  Option[Outcome] = Option.empty[Outcome]
  private var status:      Status          = Alive
  private var updatePhase: Phase           = Morning
  private var updateDay:   Int             = 1

  def setOutcome(outcome: Outcome): Unit = {
    outcomeOpt = Option(outcome)
  }

  def getOutcomeOpt: Option[Outcome] = outcomeOpt

  def getStatus(phase: Phase, day: Int): Status = {
    import cats.implicits._
    if (updateDay === day &&
        updatePhase.label === Noon.label &&
        phase.label === Night.label) {
      Alive
    } else {
      status
    }
  }

  def setStatus(status: Status): Unit = {
    this.status = status
  }

  def setUpdatePhase(phase: Phase): Unit = {
    this.updatePhase = phase
  }

  def getUpdatePhase: Phase = updatePhase

  def setUpdateDay(day: Int): Unit = {
    this.updateDay = day
  }

  def getUpdateDay: Int = updateDay
}

final case class OnymousAudienceInVillage() extends AvatarInVillage()

final case class AnonymousAudienceInVillage() extends AvatarInVillage()
