package licos.json.validation.auth

object ResponseValidation {

  import play.api.libs.json.Reads
  import play.api.libs.json.Reads.pattern

  object server2robot {
    val authenticationRequestResponse: Reads[String] =
      pattern(
        """(?:authenticated|error:(?:alreadyRegistered|blocked|need(?:User(?:Email|Password)|AccessToken|RobotVersion|SourceCodeTimestamp)|invalid(?:User(?:Email|Password)|AccessToken|Version|SourceCodeTimestamp|ProgrammingLanguage(?:Name|Version))?Format|loginCredentialsNotFound|accessTokenNotFound))""".r
      )
    val authorizationRequestResponse: Reads[String] =
      pattern(
        """(?:authorized|error:(?:alreadyRegistered|blocked|need(?:User(?:Email|Password)|AccessToken|RobotVersion|SourceCodeTimestamp)|invalid(?:User(?:Email|Password)|AccessToken|Version|SourceCodeTimestamp|ProgrammingLanguage(?:Name|Version))?Format|loginCredentialsNotFound|accessTokenNotFound))""".r
      )
  }

  val server2client: Reads[String] = pattern(
    """(?:authorized|error:(?:invalidFormat|needAccessToken|accessTokenNotFound))""".r
  )
}
