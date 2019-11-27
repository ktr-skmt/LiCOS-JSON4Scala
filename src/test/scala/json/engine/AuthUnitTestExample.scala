package json.engine

abstract class AuthUnitTestExample(filePath: String) {
  private val baseUrl: String = "https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/auth/unitTest/0.3/"

  val path: String = baseUrl.concat(filePath)

  val `type`: String
}
