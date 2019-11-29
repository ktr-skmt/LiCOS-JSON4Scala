package licos.entity

import java.net.URL
import java.time.OffsetDateTime
import java.util.{Locale, UUID}

import licos.knowledge.{Alive, AvatarSetting, Cast, Character, Lobby, Phase, Role}
import licos.protocol.element.village.part.VillageProtocol

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.FiniteDuration

final case class Village(
    private val villageInfoFromLobby: VillageInfoFromLobby,
    private val village:              VillageProtocol,
    token:                            UUID,
    phase:                            Phase,
    day:                              Int,
    phaseTimeLimit:                   FiniteDuration,
    phaseStartTime:                   OffsetDateTime,
    avatars:                          Seq[AvatarInVillage],
    chatId:                           Int,
    myCharacter:                      Character,
    myRole:                           Role,
    myAvatarName:                     String,
    myAvatarImage:                    URL
) {

  def id:                           Long   = village.id
  def name:                         String = village.name
  def language:                     Locale = village.language
  def totalNumberOfPlayers:         Int    = village.totalNumberOfPlayers
  def maxNumberOfChatMessages:      Int    = village.chatSettings.maxNumberOfChatMessages
  def maxLengthOfUnicodeCodePoints: Int    = village.chatSettings.maxLengthOfUnicodeCodePoints

  def cast:                    Cast           = villageInfoFromLobby.cast
  def comment:                 Option[String] = villageInfoFromLobby.comment
  def hostPlayer:              HostPlayer     = villageInfoFromLobby.hostPlayer
  def idForSearching:          Int            = villageInfoFromLobby.idForSearching
  def lobby:                   Lobby          = villageInfoFromLobby.lobby
  def avatarSetting:           AvatarSetting  = villageInfoFromLobby.avatarSetting
  def maxNumberOfHumanPlayers: Int            = villageInfoFromLobby.maxNumberOfHumanPlayers

  @SuppressWarnings(Array[String]("org.wartremover.warts.MutableDataStructures"))
  def alivePlayers: Seq[PlayerInVillage] = {
    val playerBuffer = ListBuffer.empty[PlayerInVillage]
    import cats.implicits._
    avatars foreach {
      case player: PlayerInVillage if player.status(phase, day).label === Alive.label =>
        playerBuffer += player
      case _ =>
    }
    playerBuffer.result
  }

  def numberOfAlivePlayers: Int = alivePlayers.size

  def isAvailable: Boolean = true
}
