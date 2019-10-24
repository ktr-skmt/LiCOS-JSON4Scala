package engine

abstract class LobbyUnitTestExample(filePath: String) {
  private val baseUrl: String = "https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/lobby/unitTest/0.3/"

  val path: String = baseUrl.concat(filePath)

  val `type`: String
}