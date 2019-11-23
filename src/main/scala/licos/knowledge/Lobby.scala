package licos.knowledge

sealed abstract class Lobby(val label: String) {

  override def toString: String = label

}

case object HumanPlayerLobby extends Lobby("human player")
case object RobotPlayerLobby extends Lobby("robot player")
case object OnymousAudienceLobby extends Lobby("onymous audience")
case object AnonymousAudienceLobby extends Lobby("anonymous audience")
