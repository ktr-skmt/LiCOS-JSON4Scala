package licos.protocol

import licos.knowledge.Channel

sealed abstract class PlayerChatChannel(label: String) {
  val channel: Channel
}

case object PublicChannel extends PlayerChatChannel(licos.knowledge.PublicChannel.label) {
  override val channel: Channel = licos.knowledge.PublicChannel
}

case object PrivateChannel extends PlayerChatChannel(licos.knowledge.PrivateChannel.label) {
  override val channel: Channel = licos.knowledge.PrivateChannel
}

case object WerewolfChannel extends PlayerChatChannel(licos.knowledge.WerewolfChannel.label) {
  override val channel: Channel = licos.knowledge.WerewolfChannel
}

case object GraveChannel extends PlayerChatChannel(licos.knowledge.GraveChannel.label) {
  override val channel: Channel = licos.knowledge.GraveChannel
}
