package protocol.engine

abstract class AuthExample(filePath: String) {
  private val baseUrl: String = "https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/auth/example/0.3/"

  val path: String = baseUrl.concat(filePath)

  val `type`: String
}

abstract class RobotToServerAuthExample(filePath: String)
    extends AuthExample(RobotToServerAuthExample.robot2server(filePath))

object RobotToServerAuthExample {
  def robot2server(filePath: String): String = "robot2server/".concat(filePath)
}

abstract class ServerToRobotAuthExample(filePath: String)
    extends AuthExample(ServerToRobotAuthExample.server2robot(filePath))

object ServerToRobotAuthExample {
  def server2robot(filePath: String): String = "server2robot/".concat(filePath)
}
