package licos.knowledge

sealed class PingStatus(val label: String) {
  override def toString: String = label
}

case object PingDanger extends PingStatus("danger")
case object PingWarning extends PingStatus("warning")
case object PingSafe extends PingStatus("safe")
