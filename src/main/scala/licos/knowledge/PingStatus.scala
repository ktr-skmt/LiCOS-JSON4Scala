package licos.knowledge

sealed abstract class PingStatus(val label: String) extends Product with Serializable {
  override def toString: String = label
}

case object PingDanger extends PingStatus("danger")
case object PingWarning extends PingStatus("warning")
case object PingSafe extends PingStatus("safe")
