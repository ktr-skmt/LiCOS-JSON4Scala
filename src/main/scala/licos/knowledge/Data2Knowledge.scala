package licos.knowledge

import licos.json.element.village.JsonName
import licos.protocol.PlayerChatChannel
import licos.protocol.element.village.part.NameProtocol

object Data2Knowledge {

  def architectureOpt(label: String): Option[Architecture] = {
    label match {
      case HumanArchitecture.label               => Some(HumanArchitecture)
      case FullyAutomatedRobotArchitecture.label => Some(FullyAutomatedRobotArchitecture)
      case SemiAutomatedRobotArchitecture.label  => Some(SemiAutomatedRobotArchitecture)
      case _                                     => Option.empty[Architecture]
    }
  }

  def polarityMarkOpt(label: String): Option[PolarityMark] = {
    label match {
      case QuestionMark.label => Some(QuestionMark)
      case CrossMark.label    => Some(CrossMark)
      case TriangleMark.label => Some(TriangleMark)
      case CircleMark.label   => Some(CircleMark)
      case _                  => Option.empty[PolarityMark]
    }
  }

  def severityOpt(label: String): Option[Severity] = {
    label match {
      case FatalSeverity.label   => Some(FatalSeverity)
      case ErrorSeverity.label   => Some(ErrorSeverity)
      case WarningSeverity.label => Some(WarningSeverity)
      case InfoSeverity.label    => Some(InfoSeverity)
      case _                     => Option.empty[Severity]
    }
  }

  def statusOpt(label: String): Option[Status] = {
    label match {
      case Alive.label            => Some(Alive)
      case Dead.label             => Some(Dead)
      case DeathByExecution.label => Some(DeathByExecution)
      case DeathByAttack.label    => Some(DeathByAttack)
      case DeathByFear.label      => Some(DeathByFear)
      case UnnaturalDeath.label   => Some(UnnaturalDeath)
      case _                      => Option.empty[Status]
    }
  }

  def pingStatusOpt(label: String): Option[PingStatus] = {
    label match {
      case PingDanger.label  => Some(PingDanger)
      case PingWarning.label => Some(PingWarning)
      case PingSafe.label    => Some(PingSafe)
      case _                 => Option.empty[PingStatus]
    }
  }

  def outcomeOpt(label: String): Option[Outcome] = {
    label match {
      case Victory.label => Some(Victory)
      case Defeat.label  => Some(Defeat)
      case _             => Option.empty[Outcome]
    }
  }

  def lobbyOpt(label: String): Option[Lobby] = {
    label match {
      case HumanPlayerLobby.label       => Some(HumanPlayerLobby)
      case RobotPlayerLobby.label       => Some(RobotPlayerLobby)
      case OnymousAudienceLobby.label   => Some(OnymousAudienceLobby)
      case AnonymousAudienceLobby.label => Some(AnonymousAudienceLobby)
      case _                            => Option.empty[Lobby]
    }
  }

  def name(json: JsonName): NameProtocol = {
    val name = NameProtocol().en(json.en)
    json.ar.foreach(name.ar)
    json.de.foreach(name.de)
    json.es.foreach(name.es)
    json.fr.foreach(name.fr)
    json.it.foreach(name.it)
    json.ja.foreach(name.ja)
    json.pt.foreach(name.pt)
    json.ru.foreach(name.ru)
    json.uk.foreach(name.uk)
    json.vi.foreach(name.vi)
    json.zhCN.foreach(name.zhCN)
    json.zhTW.foreach(name.zhTW)
    name
  }

  def playerChatChannelOpt(label: String): Option[PlayerChatChannel] = {
    label match {
      case licos.protocol.PublicChannel.channel.label   => Some(licos.protocol.PublicChannel)
      case licos.protocol.PrivateChannel.channel.label  => Some(licos.protocol.PrivateChannel)
      case licos.protocol.WerewolfChannel.channel.label => Some(licos.protocol.WerewolfChannel)
      case licos.protocol.GraveChannel.channel.label    => Some(licos.protocol.GraveChannel)
      case _                                            => Option.empty[PlayerChatChannel]
    }
  }

  def phaseOpt(label: String): Option[Phase] = {
    label match {
      case FlavorText.label           => Some(FlavorText)
      case Morning.label              => Some(Morning)
      case Noon.label                 => Some(Noon)
      case Night.label                => Some(Night)
      case Result.label               => Some(Result)
      case PostMortemDiscussion.label => Some(PostMortemDiscussion)
      case _                          => Option.empty[Phase]
    }
  }

  def roleOpt(name: String, numberOfPlayers: Int): Option[Role] = {
    name.toLowerCase match {
      case "villager"    => Some(VillagerRole(numberOfPlayers))
      case "werewolf"    => Some(WerewolfRole(numberOfPlayers))
      case "seer"        => Some(SeerRole(numberOfPlayers))
      case "medium"      => Some(MediumRole(numberOfPlayers))
      case "hunter"      => Some(HunterRole(numberOfPlayers))
      case "mason"       => Some(MasonRole(numberOfPlayers))
      case "madman"      => Some(MadmanRole(numberOfPlayers))
      case "werehamster" => Some(WerehamsterRole(numberOfPlayers))
      case "master"      => Some(MasterRole(numberOfPlayers))
      case _             => Option.empty[Role]
    }
  }

  def characterOpt(name: String, id: Int): Option[Character] = {
    name.headOption.flatMap { head: Char =>
      java.lang.Character.toLowerCase(head) match {
        case 'a' => Some(Adil())
        case 'b' => Some(Borya())
        case 'c' => Some(Chacha())
        case 'd' => Some(Devdatta())
        case 'e' => Some(Ekrem())
        case 'f' => Some(Fernando())
        case 'g' => Some(Gavriil())
        case 'h' => Some(Henrik())
        case 'i' => Some(Ileanna())
        case 'j' => Some(Jasmin())
        case 'k' => Some(Kaiji())
        case 'l' => Some(Louise())
        case 'm' => Some(Marthe())
        case 'n' => Some(Nanyamka())
        case 'o' => Some(Oliwia())
        case 'r' => Some(Ryan())
        case 's' => Some(Susan())
        case 't' => Some(Thuy())
        case 'u' => Some(Uma())
        case 'v' => Some(Valeria())
        case 'y' => Some(Yihan())
        case _   => Option.empty[Character]
      }
    }.map(_.setId(id))
  }

  def avatarSettingOpt(label: String): Option[AvatarSetting] = {
    label match {
      case RandomAvatarSetting.label => Some(RandomAvatarSetting)
      case FixedAvatarSetting.label  => Some(FixedAvatarSetting)
      case _                         => Option.empty[AvatarSetting]
    }
  }

  def robotPlayerStatusOpt(label: String): Option[RobotPlayerStatus] = {
    label match {
      case Connected.label                 => Some(Connected)
      case AwaitingAuthorization.label     => Some(AwaitingAuthorization)
      case AwaitingCommunicationTest.label => Some(AwaitingCommunicationTest)
      case RunningInTheBackground.label    => Some(RunningInTheBackground)
      case RunningInTheForeground.label    => Some(RunningInTheForeground)
      case _                               => Option.empty[RobotPlayerStatus]
    }
  }

}
