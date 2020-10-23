package licos.entity

import java.time.OffsetDateTime
import java.util.{Locale, UUID}

import licos.knowledge.{AvatarSetting, Composition, Lobby, Phase}
import licos.protocol.element.village.part.VillageProtocol

import scala.concurrent.duration.FiniteDuration

final case class VillageInfo(
    private val villageInfoFromLobby: VillageInfoFromLobby,
    private val village:              VillageProtocol,
    token:                            UUID,
    phase:                            Phase,
    day:                              Int,
    phaseTimeLimit:                   FiniteDuration,
    phaseStartTime:                   OffsetDateTime,
    clientTimestampOpt:               Option[OffsetDateTime],
    serverTimestampOpt:               Option[OffsetDateTime]
) extends Serializable {
  def id:                           Long   = village.id
  def name:                         String = village.name
  def language:                     Locale = village.language
  def totalNumberOfPlayers:         Int    = village.totalNumberOfPlayers
  def maxNumberOfChatMessages:      Int    = village.chatSettings.maxNumberOfChatMessages
  def maxLengthOfUnicodeCodePoints: Int    = village.chatSettings.maxLengthOfUnicodeCodePoints

  def composition:             Composition    = villageInfoFromLobby.composition
  def comment:                 Option[String] = villageInfoFromLobby.comment
  def hostPlayer:              HostPlayer     = villageInfoFromLobby.hostPlayer
  def idForSearching:          Long           = villageInfoFromLobby.idForSearching
  def lobby:                   Lobby          = villageInfoFromLobby.lobby
  def avatarSetting:           AvatarSetting  = villageInfoFromLobby.avatarSetting
  def maxNumberOfHumanPlayers: Int            = villageInfoFromLobby.maxNumberOfHumanPlayers

  def updateClientTimestamp(clientTimestamp: OffsetDateTime): VillageInfo = {
    VillageInfoFactory
      .create(
        villageInfoFromLobby,
        id,
        name,
        totalNumberOfPlayers,
        language,
        maxNumberOfChatMessages,
        maxLengthOfUnicodeCodePoints,
        token,
        phase,
        day,
        phaseTimeLimit.toSeconds.toInt,
        phaseStartTime,
        Option(clientTimestamp),
        serverTimestampOpt
      )
  }

  def updateServerTimestamp(serverTimestamp: OffsetDateTime): VillageInfo = {
    VillageInfoFactory
      .create(
        villageInfoFromLobby,
        id,
        name,
        totalNumberOfPlayers,
        language,
        maxNumberOfChatMessages,
        maxLengthOfUnicodeCodePoints,
        token,
        phase,
        day,
        phaseTimeLimit.toSeconds.toInt,
        phaseStartTime,
        clientTimestampOpt,
        Option(serverTimestamp)
      )
  }
}
