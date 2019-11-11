package licos.json.validation.lobby

object LobbyValidation {

  import play.api.libs.json._
  import play.api.libs.json.Reads._

  val lobby: Reads[String] = pattern("""(?:(?:human|robot) player|(?:an)?onymous audience)""".r)
  val page:  Reads[Int]    = min(1)
}
