package licos.entity

import java.util.Locale

import licos.knowledge.{AvatarSetting, Cast, Character, Lobby, Role}

import scala.collection.mutable
import scala.util.Try

@SuppressWarnings(Array[String]("org.wartremover.warts.Var"))
class VillageFactory {

  private var id:                           Option[Long]                                = None
  private var name:                         Option[String]                              = None
  private var lobby:                        Option[Lobby]                               = None
  private var hostPlayer:                   Option[HostPlayer]                          = None
  private var maxNumberOfHumanPlayers:      Option[Int]                                 = None
  private var maxNumberOfChatMessages:      Option[Int]                                 = None
  private var maxLengthOfUnicodeCodePoints: Option[Int]                                 = None
  private var avatarSetting:                Option[AvatarSetting]                       = None
  private var language:                     Option[Locale]                              = None
  private var comment:                      Option[String]                              = None
  private var idForSearching:               Option[Int]                                 = None
  private var story:                        Option[Int]                                 = None
  private var cast:                         Option[Cast]                                = None
  private var avatars:                      Option[mutable.ListBuffer[AvatarInVillage]] = None

  def canCreate: Boolean = {
    id.nonEmpty &&
    name.nonEmpty &&
    lobby.nonEmpty &&
    hostPlayer.nonEmpty &&
    avatarSetting.nonEmpty &&
    maxNumberOfHumanPlayers.nonEmpty &&
    maxNumberOfChatMessages.nonEmpty &&
    maxLengthOfUnicodeCodePoints.nonEmpty &&
    language.nonEmpty &&
    idForSearching.nonEmpty &&
    story.nonEmpty &&
    cast.nonEmpty &&
    avatars.nonEmpty
  }

  def create: Try[Village] = {
    if (canCreate) {
      Try {
        Village(
          id.get,
          name.get,
          lobby.get,
          hostPlayer.get,
          avatarSetting.get,
          maxNumberOfHumanPlayers.get,
          maxNumberOfChatMessages.get,
          maxLengthOfUnicodeCodePoints.get,
          language.get,
          comment,
          idForSearching.get,
          story.get,
          cast.get,
          avatars.get
        )
      }
    } else {
      throw new VillageCreationException()
    }
  }

  private class VillageCreationException extends Exception

  def setId(id: Long): VillageFactory = {
    this.id = Option(id)
    this
  }

  def setName(name: String): VillageFactory = {
    this.name = Option(name)
    this
  }

  def setLobby(lobby: Lobby): VillageFactory = {
    this.lobby = Option(lobby)
    this
  }

  def setHostPlayer(hostPlayer: HostPlayer): VillageFactory = {
    this.hostPlayer = Option(hostPlayer)
    this
  }

  def setAvatarSetting(avatarSetting: AvatarSetting): VillageFactory = {
    this.avatarSetting = Option(avatarSetting)
    this
  }

  def setMaxNumberOfHumanPlayers(maxNumberOfHumanPlayers: Int): VillageFactory = {
    this.maxNumberOfHumanPlayers = Option(maxNumberOfHumanPlayers)
    this
  }

  def setMaxNumberOfChatMessages(maxNumberOfChatMessages: Int): VillageFactory = {
    this.maxNumberOfChatMessages = Option(maxNumberOfChatMessages)
    this
  }

  def setMaxLengthOfUnicodeCodePoints(maxLengthOfUnicodeCodePoints: Int): VillageFactory = {
    this.maxLengthOfUnicodeCodePoints = Option(maxLengthOfUnicodeCodePoints)
    this
  }

  def setLanguage(language: Locale): VillageFactory = {
    this.language = Option(language)
    this
  }

  def setComment(comment: String): VillageFactory = {
    this.comment = Option(comment)
    this
  }

  def setIdForSearching(idForSearching: Int): VillageFactory = {
    this.idForSearching = Option(idForSearching)
    this
  }

  def setStory(story: Int): VillageFactory = {
    this.story = Option(story)
    this
  }

  def setCast(cast: Cast): VillageFactory = {
    this.cast = Option(cast)
    this
  }

  def setAvatars(avatars: mutable.ListBuffer[AvatarInVillage]): VillageFactory = {
    this.avatars = Option(avatars)
    this
  }

}
