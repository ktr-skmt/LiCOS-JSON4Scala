package licos.entity

import java.sql.Timestamp
import java.util.UUID

import licos.knowledge.{
  Alive,
  Character,
  HunterRole,
  Morning,
  Night,
  Noon,
  Outcome,
  Phase,
  Role,
  SeerRole,
  Status,
  WerewolfRole
}

sealed abstract class AvatarInVillageForServer()

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
final case class PlayerInVillageForServer(character: Character, role: Role, onlyServer: ServerOnly)
    extends AvatarInVillageForServer() {

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

  private var playerPutToDeath: Option[PlayerInVillageForServer] = Option.empty[PlayerInVillageForServer]

  def canPutToDeath(player: PlayerInVillageForServer): Boolean = {
    status.isAlive && player.status.isAlive
  }

  def putToDeath(player: PlayerInVillageForServer): Unit = {
    playerPutToDeath = Option(player).filter(canPutToDeath)
  }

  def getPlayerPutToDeath: Option[PlayerInVillageForServer] = playerPutToDeath

  private var playerLearntToTheTruth: Option[PlayerInVillageForServer] = Option.empty[PlayerInVillageForServer]

  def canLearnTheTruth(player: PlayerInVillageForServer, phase: Phase, day: Int): Boolean = {
    if (getStatus(phase, day).isAlive && player.getStatus(phase, day).isAlive) {
      role match {
        case SeerRole(_) => true
        case _           => false
      }
    } else {
      false
    }
  }

  def learnTheTruth(player: PlayerInVillageForServer, phase: Phase, day: Int): Unit = {
    playerLearntToTheTruth = Option(player).filter(canLearnTheTruth(_, phase, day))
  }

  def getPlayerLearnToTheTruth: Option[PlayerInVillageForServer] = playerLearntToTheTruth

  private var playerGuarded: Option[PlayerInVillageForServer] = Option.empty[PlayerInVillageForServer]

  def canGuard(player: PlayerInVillageForServer, phase: Phase, day: Int): Boolean = {
    if (getStatus(phase, day).isAlive && player.getStatus(phase, day).isAlive) {
      role match {
        case HunterRole(_) => true
        case _             => false
      }
    } else {
      false
    }
  }

  def guard(player: PlayerInVillageForServer, phase: Phase, day: Int): Unit = {
    playerGuarded = Option(player).filter(canGuard(_, phase, day))
  }

  def getPlayerGuarded: Option[PlayerInVillageForServer] = playerGuarded

  private var playerAttacked: Option[PlayerInVillageForServer] = Option.empty[PlayerInVillageForServer]

  def canAttack(player: PlayerInVillageForServer, phase: Phase, day: Int): Boolean = {
    if (getStatus(phase, day).isAlive && player.getStatus(phase, day).isAlive) {
      role match {
        case WerewolfRole(_) => true
        case _               => false
      }
    } else {
      false
    }
  }

  def attack(player: PlayerInVillageForServer, phase: Phase, day: Int): Unit = {
    playerAttacked = Option(player).filter(canAttack(_, phase, day))
  }

  def getPlayerAttacked: Option[PlayerInVillageForServer] = playerAttacked

}

final case class OnymousAudienceInVillageForServer(
    id:        Long,
    token:     UUID,
    createdAt: Timestamp,
    updatedAt: Timestamp,
    name:      String,
    image:     String,
    isPrimary: Boolean
) extends AvatarInVillageForServer()

final case class AnonymousAudienceInVillageForServer() extends AvatarInVillageForServer()
