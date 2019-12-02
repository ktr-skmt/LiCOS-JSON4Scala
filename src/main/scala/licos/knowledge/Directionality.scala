package licos.knowledge

sealed class Directionality(val label: String) {
  override def toString: String = label
}

case object ServerToClient extends Directionality("server to client")
case object ClientToServer extends Directionality("client to server")
