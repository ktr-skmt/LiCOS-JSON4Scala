package licos.json.validation.auth

object ResponseValidation {

  import play.api.libs.json.Reads
  import play.api.libs.json.Reads.pattern

  val server2robot: Reads[String] = pattern(
    """(?:authenticated|authorized|error:(?:alreadyRegistered|blocked|need(?:User(?:Email|Password)|AccessToken|RobotVersion|SourceCodeTimestamp)|invalid(?:User(?:Email|Password)|AccessToken|Version|SourceCodeTimestamp|ProgrammingLanguage(?:Name|Version))?Format|loginCredentialsNotFound|accessTokenNotFound))""".r
  )
  val server2client: Reads[String] = pattern(
    """(?:authorized|error:(?:invalidFormat|needAccessToken|accessTokenNotFound))""".r
  )
}
