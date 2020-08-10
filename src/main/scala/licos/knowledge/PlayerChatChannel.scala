package licos.knowledge

sealed abstract class PlayerChatChannel(val channel: Channel) extends Product with Serializable

case object PublicChatChannel extends PlayerChatChannel(PublicChannel)

case object PrivateChatChannel extends PlayerChatChannel(PrivateChannel)

case object WerewolfChatChannel extends PlayerChatChannel(WerewolfChannel)

case object GraveChatChannel extends PlayerChatChannel(GraveChannel)

case object OnymousAudienceChatChannel extends PlayerChatChannel(OnymousAudienceChannel)

case object AnonymousAudienceChatChannel extends PlayerChatChannel(AnonymousAudienceChannel)
