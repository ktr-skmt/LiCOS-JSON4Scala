package licos.entity

import java.sql.Timestamp
import java.util.UUID

import licos.knowledge.{
  Alive,
  Architecture,
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

sealed abstract class AvatarInVillage()

final case class PlayerInVillage(
    village:      Village,
    id:           Long,
    token:        UUID,
    createdAt:    Timestamp,
    updatedAt:    Timestamp,
    name:         String,
    image:        String,
    isPrimary:    Boolean,
    architecture: Architecture,
    isHost:       Boolean,
    character:    Character,
    role:         Role
) extends AvatarInVillage() {

  var outcomeOpt: Option[Outcome] = Option.empty[Outcome]

  private var status:      Status = Alive
  private var updatePhase: Phase  = Morning
  private var updateDay:   Int    = 1

  def status(phase: Phase, day: Int): Status = {
    if (updateDay == day && updatePhase == Noon && phase == Night) {
      Alive
    } else {
      status
    }
  }

  def status(status: Status): Unit = {
    this.status = status
  }

  def updatePhase(phase: Phase): Unit = {
    this.updatePhase = phase
  }

  def updateDay(day: Int): Unit = {
    this.updateDay = day
  }

  var playerPutToDeath: Option[PlayerInVillage] = Option.empty[PlayerInVillage]

  def canPutToDeath(player: PlayerInVillage): Boolean = {
    status.isAlive && player.status.isAlive
  }

  def putToDeath(player: PlayerInVillage): Unit = {
    playerPutToDeath = Option(player).filter(canPutToDeath)
  }

  var playerLearntToTheTruth: Option[PlayerInVillage] = Option.empty[PlayerInVillage]

  def canLearnTheTruth(player: PlayerInVillage, phase: Phase, day: Int): Boolean = {
    if (status(phase, day).isAlive && player.status(phase, day).isAlive) {
      role match {
        case SeerRole(_) => true
        case _           => false
      }
    } else {
      false
    }
  }

  def learnTheTruth(player: PlayerInVillage, phase: Phase, day: Int): Unit = {
    playerLearntToTheTruth = Option(player).filter(canLearnTheTruth(_, phase, day))
  }

  var playerGuarded: Option[PlayerInVillage] = Option.empty[PlayerInVillage]

  def canGuard(player: PlayerInVillage, phase: Phase, day: Int): Boolean = {
    if (status(phase, day).isAlive && player.status(phase, day).isAlive) {
      role match {
        case HunterRole(_) => true
        case _             => false
      }
    } else {
      false
    }
  }

  def guard(player: PlayerInVillage, phase: Phase, day: Int): Unit = {
    playerGuarded = Option(player).filter(canGuard(_, phase, day))
  }

  var playerAttacked: Option[PlayerInVillage] = Option.empty[PlayerInVillage]

  def canAttack(player: PlayerInVillage, phase: Phase, day: Int): Boolean = {
    if (status(phase, day).isAlive && player.status(phase, day).isAlive) {
      role match {
        case WerewolfRole(_) => true
        case _               => false
      }
    } else {
      false
    }
  }

  def attack(player: PlayerInVillage, phase: Phase, day: Int): Unit = {
    playerAttacked = Option(player).filter(canAttack(_, phase, day))
  }

}

final case class OnymousAudienceInVillage(
    village:   Village,
    id:        Long,
    token:     UUID,
    createdAt: Timestamp,
    updatedAt: Timestamp,
    name:      String,
    image:     String,
    isPrimary: Boolean
) extends AvatarInVillage()

final case class AnonymousAudienceInVillage(village: Village) extends AvatarInVillage()
