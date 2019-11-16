package licos.state

import java.time.OffsetDateTime
import java.util.{Locale, UUID}

import licos.json.element.lobby.{JsonHostPlayer, JsonPlayerSetting, JsonRoleSetting}
import licos.knowledge.{Character, Phase, Role, Status}

import scala.collection.mutable

@SuppressWarnings(Array[String]("org.wartremover.warts.Var"))
class VillageState(val villageId:                    Long,
                   val villageName:                  String,
                   val totalNumberOfCharacters:      Int,
                   val lang:                         Locale,
                   val maxNumberOfChatMessages:      Int,
                   val maxLengthOfUnicodeCodePoints: Int,
                   val token:                        UUID,
                   val avatarName:                   String,
                   val avatarImage:                  String,
                   val idForSearching:               Int,
                   val hostPlayer:                   JsonHostPlayer,
                   val playerSetting:                JsonPlayerSetting,
                   val roleSetting:                  JsonRoleSetting,
                   val comment:                      Option[String],
                   val myCharacter:                  Character,
                   val myRole:                       Role,
                   val allCharacters:                Seq[Character],
                   val allRoles:                     Seq[Role],
                   val characterStatusMap:           mutable.Map[Character, Status]) {

  var phase:          Option[Phase]          = None
  var day:            Option[Int]            = None
  var phaseTimeLimit: Option[Int]            = None
  var phaseStartTime: Option[OffsetDateTime] = None

  def isAvailable: Boolean = {
    phase.nonEmpty &&
    day.nonEmpty &&
    phaseTimeLimit.nonEmpty &&
    phaseStartTime.nonEmpty
  }

}
