package licos.entity

import java.net.URL

import licos.knowledge.{Architecture, AvatarSetting, Cast, Lobby}

final case class VillageInfoFromLobby(
    lobby:                   Lobby,
    hostPlayer:              HostPlayer,
    cast:                    Cast,
    idForSearching:          Int,
    avatarSetting:           AvatarSetting,
    maxNumberOfHumanPlayers: Int,
    comment:                 Option[String],
    myAvatarName:            String,
    myAvatarImage:           URL
)

final case class HostPlayer(id: Long, name: String, isAnonymous: Boolean, architecture: Architecture)
