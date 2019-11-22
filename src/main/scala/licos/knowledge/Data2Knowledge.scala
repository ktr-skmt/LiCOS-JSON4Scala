package licos.knowledge

import licos.json.element.village.JsonName
import licos.protocol.PlayerChatChannel
import licos.protocol.element.village.part.NameProtocol

object Data2Knowledge {

  def polarityMarkOpt(label: String): Option[PolarityMark] = {
    label match {
      case QuestionMark.label => Some(QuestionMark)
      case CrossMark.label    => Some(CrossMark)
      case TriangleMark.label => Some(TriangleMark)
      case CircleMark.label   => Some(CircleMark)
      case _                  => None
    }
  }

  def severityOpt(label: String): Option[Severity] = {
    label match {
      case FatalSeverity.label   => Some(FatalSeverity)
      case ErrorSeverity.label   => Some(ErrorSeverity)
      case WarningSeverity.label => Some(WarningSeverity)
      case InfoSeverity.label    => Some(InfoSeverity)
      case _                     => None
    }
  }

  def statusOpt(label: String): Option[Status] = {
    label match {
      case Alive.label            => Option(Alive)
      case Dead.label             => Option(Dead)
      case DeathByExecution.label => Option(DeathByExecution)
      case DeathByAttack.label    => Option(DeathByAttack)
      case DeathByFear.label      => Option(DeathByFear)
      case UnnaturalDeath.label   => Option(UnnaturalDeath)
      case _                      => None
    }
  }

  def outcomeOpt(label: String): Option[Outcome] = {
    label match {
      case Victory.label => Some(Victory)
      case Defeat.label  => Some(Defeat)
      case _             => None
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
      case _                                            => None
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
      case _                          => None
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
      case _             => None
    }
  }

  def characterOpt(name: String, id: Int): Option[Character] = {
    val retOpt: Option[Character] = {
      java.lang.Character.toLowerCase(name.head) match {
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
        case _   => None
      }
    }
    retOpt.map(_.setId(id))
  }

}
