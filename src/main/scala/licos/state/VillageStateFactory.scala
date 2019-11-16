package licos.state

import java.util.{Locale, UUID}

import scala.util.Try

@SuppressWarnings(Array[String]("org.wartremover.warts.Var"))
class VillageStateFactory {

  private var villageId:                    Option[Long]   = None
  private var villageName:                  Option[String] = None
  private var totalNumberOfCharacters:      Option[Int]    = None
  private var lang:                         Option[Locale] = None
  private var maxNumberOfChatMessages:      Option[Int]    = None
  private var maxLengthOfUnicodeCodePoints: Option[Int]    = None
  private var token:                        Option[UUID]   = None

  def canCreate: Boolean = {
    villageId.nonEmpty &&
      villageName.nonEmpty &&
      totalNumberOfCharacters.nonEmpty &&
      lang.nonEmpty &&
      maxNumberOfChatMessages.nonEmpty &&
      maxLengthOfUnicodeCodePoints.nonEmpty &&
      token.nonEmpty
  }

  def create: Try[VillageState] = Try {
    if (canCreate) {
      new VillageState(
        villageId.get,
        villageName.get,
        totalNumberOfCharacters.get,
        lang.get,
        maxNumberOfChatMessages.get,
        maxLengthOfUnicodeCodePoints.get,
        token.get)
    } else {
      throw new GameStateCreationException()
    }
  }

  private class GameStateCreationException extends Exception

  def setVillageId(villageId: Long): VillageStateFactory = {
    this.villageId = Option(villageId)
    this
  }

  def setVillageName(villageName: String): VillageStateFactory = {
    this.villageName = Option(villageName)
    this
  }

  def setTotalNumberOfCharacters(totalNumberOfCharacters: Int): VillageStateFactory = {
    this.totalNumberOfCharacters = Option(totalNumberOfCharacters)
    this
  }

  def setLang(lang: Locale): VillageStateFactory = {
    this.lang = Option(lang)
    this
  }

  def setMaxNumberOfChatMessages(maxNumberOfChatMessage: Int): VillageStateFactory = {
    this.maxNumberOfChatMessages = Option(maxNumberOfChatMessage)
    this
  }

  def setMaxLengthOfUnicodeCodePoints(maxLengthOfUnicodeCodePoints: Int): VillageStateFactory = {
    this.maxLengthOfUnicodeCodePoints = Option(maxLengthOfUnicodeCodePoints)
    this
  }

  def setToken(token: UUID): VillageStateFactory = {
    this.token = Option(token)
    this
  }

}
