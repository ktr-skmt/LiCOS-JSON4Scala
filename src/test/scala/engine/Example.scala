package engine

abstract class Example(filePath: String) {
  private val baseUrl: String = "https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/example/0.2/"

  val path: String = baseUrl.concat(filePath)

  val `type`: String
}
