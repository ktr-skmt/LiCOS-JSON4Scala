package licos.entity

import java.time.OffsetDateTime
import java.util.{Locale, UUID}

import licos.knowledge.{Alive, Architecture, AvatarSetting, Cast, Character, Morning, Phase, Role}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

final case class Village(
    id:                           Long,
    name:                         String,
    hostPlayer:                   HostPlayer,
    avatarSetting:                AvatarSetting,
    maxNumberOfHumanPlayers:      Int,
    maxNumberOfChatMessages:      Int,
    maxLengthOfUnicodeCodePoints: Int,
    language:                     Locale,
    comment:                      Option[String],
    idForSearching:               Int,
    story:                        Int,
    cast:                         Cast,
    avatars:                      mutable.ListBuffer[AvatarInVillage]
) {

  var currentPhase:      Phase                   = Morning
  var currentDay:        Int                     = 1
  var phaseStartTimeOpt: Option[OffsetDateTime]  = Option.empty[OffsetDateTime]
  var currentChatId:     Int                     = 0
  var myAvatarOpt:       Option[AvatarInVillage] = Option.empty[AvatarInVillage]
  var myCharacterOpt:    Option[Character]       = Option.empty[Character]
  var myRoleOpt:         Option[Role]            = Option.empty[Role]
  def tokenOpt: Option[UUID] = {
    myAvatarOpt flatMap { avatar: AvatarInVillage =>
      avatar match {
        case player:          PlayerInVillage            => Option(player.token)
        case onymousAudience: OnymousAudienceInVillage   => Option(onymousAudience.token)
        case _:               AnonymousAudienceInVillage => None
      }
    }
  }

  def avatarNameOpt: Option[String] = {
    myAvatarOpt flatMap { avatar: AvatarInVillage =>
      avatar match {
        case player:          PlayerInVillage            => Option(player.name)
        case onymousAudience: OnymousAudienceInVillage   => Option(onymousAudience.name)
        case _:               AnonymousAudienceInVillage => None
      }
    }
  }

  def avatarImageOpt: Option[String] = {
    myAvatarOpt flatMap { avatar: AvatarInVillage =>
      avatar match {
        case player:          PlayerInVillage            => Option(player.image)
        case onymousAudience: OnymousAudienceInVillage   => Option(onymousAudience.image)
        case _:               AnonymousAudienceInVillage => None
      }
    }
  }

  def isAvailable: Boolean = {
    phaseStartTimeOpt.nonEmpty &&
    myAvatarOpt.nonEmpty &&
    myCharacterOpt.nonEmpty &&
    myRoleOpt.nonEmpty
  }

  def alivePlayers: Seq[PlayerInVillage] = {
    val playerBuffer = ListBuffer.empty[PlayerInVillage]
    avatars foreach {
      case player: PlayerInVillage if player.status(currentPhase, currentDay) == Alive =>
        playerBuffer += player
      case _ =>
    }
    playerBuffer.result
  }

  def numberOfAlivePlayers: Int = alivePlayers.size

}

final case class HostPlayer(
    id:           Long,
    name:         String,
    isAnonymous:  Boolean,
    architecture: Architecture
)
