package licos.knowledge

sealed abstract class Severity(val label: String) extends Product with Serializable {
  override def toString: String = label
}

case object FatalSeverity extends Severity("fatal")
case object ErrorSeverity extends Severity("error")
case object WarningSeverity extends Severity("warning")
case object InfoSeverity extends Severity("info")
