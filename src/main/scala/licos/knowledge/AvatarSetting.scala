package licos.knowledge

sealed abstract class AvatarSetting(val label: String) {

  override def toString: String = label

  def isAnonymous: Boolean
}

case object RandomAvatarSetting extends AvatarSetting("random") {
  override def isAnonymous: Boolean = true
}
case object FixedAvatarSetting extends AvatarSetting("fixed") {
  override def isAnonymous: Boolean = false
}
