package json.engine

abstract class LobbyExample(filePath: String) {
  private val baseUrl: String = "https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/lobby/example/0.3/"

  val path: String = baseUrl.concat(filePath)

  val `type`: String
}

abstract class ClientToServerLobbyExample(filePath: String)
    extends LobbyExample(ClientToServerVillageExample.client2server(filePath))

object ClientToServerLobbyExample {
  def client2server(filePath: String): String = "client2server/".concat(filePath)
}

abstract class ServerToClientLobbyExample(filePath: String)
    extends LobbyExample(ServerToClientLobbyExample.server2client(filePath))

object ServerToClientLobbyExample {
  def server2client(filePath: String): String = "server2client/".concat(filePath)
}

abstract class ServerToServerLobbyExample(filePath: String)
    extends LobbyExample(ServerToServerLobbyExample.server2server(filePath))

object ServerToServerLobbyExample {
  def server2server(filePath: String): String = "server2server/".concat(filePath)
}
