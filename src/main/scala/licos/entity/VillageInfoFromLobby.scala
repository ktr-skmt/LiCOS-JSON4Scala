package licos.entity

import java.net.URL

import licos.knowledge.{Architecture, AvatarSetting, Composition, Lobby}

final case class VillageInfoFromLobby(
    lobby:                   Lobby,
    hostPlayer:              HostPlayer,
    composition:             Composition,
    idForSearching:          Long,
    avatarSetting:           AvatarSetting,
    maxNumberOfHumanPlayers: Int,
    comment:                 Option[String],
    myAvatarName:            String,
    myAvatarImage:           URL
) extends Serializable

final case class HostPlayer(id: Long, name: String, isAnonymous: Boolean, architecture: Architecture)
    extends Serializable
