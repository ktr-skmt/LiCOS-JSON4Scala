package licos.entity

import java.net.URL
import java.time.OffsetDateTime
import java.util.UUID

import licos.knowledge.{Character, Phase, Role}
import licos.protocol.element.village.part.VillageProtocol

import scala.collection.mutable
import scala.concurrent.duration.FiniteDuration
import scala.util.{Failure, Success, Try}

@SuppressWarnings(
  Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.MutableDataStructures", "org.wartremover.warts.Var")
)
final case class VillageFactory(villageInfoFromLobby: VillageInfoFromLobby) {

  private var villageProtocol: Option[VillageProtocol]      = None
  private var token:           Option[UUID]                 = None
  private var phase:           Option[Phase]                = None
  private var day:             Option[Int]                  = None
  private var phaseTimeLimit:  Option[FiniteDuration]       = None
  private var phaseStartTime:  Option[OffsetDateTime]       = None
  private var avatars:         Option[Seq[AvatarInVillage]] = None
  private var chatId:          Option[Int]                  = None
  private var myAvatarName:    Option[String]               = None
  private var myAvatarImage:   Option[URL]                  = None
  private var myCharacter:     Option[Character]            = None
  private var myRole:          Option[Role]                 = None

  def canCreate: Boolean = {
    villageProtocol.nonEmpty &&
    token.nonEmpty &&
    phase.nonEmpty &&
    day.nonEmpty &&
    phaseTimeLimit.nonEmpty &&
    phaseStartTime.nonEmpty &&
    avatars.nonEmpty &&
    myCharacter.nonEmpty &&
    myRole.nonEmpty &&
    myAvatarName.nonEmpty &&
    myAvatarImage.nonEmpty
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial", "org.wartremover.warts.Nothing"))
  def create: Try[Village] = {
    if (canCreate) {
      Success {
        Village(
          villageInfoFromLobby,
          villageProtocol.get,
          token.get,
          phase.get,
          day.get,
          phaseTimeLimit.get,
          phaseStartTime.get,
          avatars.get,
          chatId.getOrElse(-1),
          myCharacter.get,
          myRole.get,
          myAvatarName.get,
          myAvatarImage.get
        )
      }
    } else {
      Failure(new VillageCreationException())
    }
  }

  private class VillageCreationException extends Exception

  def setVillageProtocol(villageProtocol: VillageProtocol): VillageFactory = {
    this.villageProtocol = Option(villageProtocol)
    this
  }

  def setToken(token: UUID): VillageFactory = {
    this.token = Option(token)
    this
  }

  def setPhase(phase: Phase): VillageFactory = {
    this.phase = Option(phase)
    this
  }

  def setDay(day: Int): VillageFactory = {
    this.day = Option(day)
    this
  }

  def setPhaseTimeLimit(phaseTimeLimit: FiniteDuration): VillageFactory = {
    this.phaseTimeLimit = Option(phaseTimeLimit)
    this
  }

  def setPhaseStartTime(phaseStartTime: OffsetDateTime): VillageFactory = {
    this.phaseStartTime = Option(phaseStartTime)
    this
  }

  def setChatId(chatId: Int): VillageFactory = {
    this.chatId = Option(chatId)
    this
  }

  def setAvatars(avatars: mutable.ListBuffer[AvatarInVillage]): VillageFactory = {
    this.avatars = Option(avatars)
    this
  }

  def setMyAvatarName(myAvatarName: String): VillageFactory = {
    this.myAvatarName = Option(myAvatarName)
    this
  }

  def setMyAvatarImage(myAvatarImage: URL): VillageFactory = {
    this.myAvatarImage = Option(myAvatarImage)
    this
  }

  def setMyCharacter(myCharacter: Character): VillageFactory = {
    this.myCharacter = Option(myCharacter)
    this
  }

  def setMyRole(myRole: Role): VillageFactory = {
    this.myRole = Option(myRole)
    this
  }
}
