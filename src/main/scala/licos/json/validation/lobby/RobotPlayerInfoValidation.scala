package licos.json.validation.lobby

object RobotPlayerInfoValidation {

  import play.api.libs.json.Reads
  import play.api.libs.json.Reads.pattern

  val status: Reads[String] = pattern(
    """(?:(?:dis)?connected|awaiting (?:authorization|communication test)|running in the (?:back|fore)ground)""".r
  )
}
