package licos.entity

import java.time.OffsetDateTime
import java.util.{Locale, UUID}

import licos.knowledge.{Architecture, AvatarSetting, Cast, Character, Phase, Role, Status}

import scala.collection.mutable

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

  var currentDay:        Int                     = Status.defaultValue.updateDay
  var currentPhase:      Phase                   = Status.defaultValue.updatePhase
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

  def numberOfAlivePlayers: Int = {}

}

final case class HostPlayer(
    id:           Long,
    name:         String,
    isAnonymous:  Boolean,
    architecture: Architecture
)
