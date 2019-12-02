package json.engine

abstract class VillageExample(filePath: String) {
  private val baseUrl: String = "https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/village/example/0.3/"

  val path: String = baseUrl.concat(filePath)

  val `type`: String
}

abstract class ClientToServerVillageExample(filePath: String)
    extends VillageExample(ClientToServerVillageExample.client2server(filePath))

object ClientToServerVillageExample {
  def client2server(filePath: String): String = "client2server/".concat(filePath)
}

abstract class ServerToClientVillageExample(filePath: String)
    extends VillageExample(ServerToClientVillageExample.server2client(filePath))

object ServerToClientVillageExample {
  def server2client(filePath: String): String = "server2client/".concat(filePath)
}

object ReceiptVillageExample {
  def receipt(filePath: String): String = "receipt/".concat(filePath)
}

object InvitationVillageExample {
  def invitation(filePath: String): String = "invitation/".concat(filePath)
}

abstract class ServerToLoggerVillageExample(filePath: String)
    extends VillageExample(ServerToLoggerVillageExample.server2logger(filePath))

object ServerToLoggerVillageExample {
  def server2logger(filePath: String): String = "server2logger/".concat(filePath)
}
