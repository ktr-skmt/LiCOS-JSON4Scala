package licos.knowledge

sealed abstract class Channel(val label: String)

case object PublicChannel extends Channel("public")
case object PrivateChannel extends Channel("private")
case object WerewolfChannel extends Channel("werewolf")
case object SeerChannel extends Channel("seer")
case object HunterChannel extends Channel("hunter")
case object MasterChannel extends Channel("master")
case object GraveChannel extends Channel("grave")
case object OnymousAudienceChannel extends Channel("onymousAudience")
case object AnonymousAudienceChannel extends Channel("anonymousAudience")