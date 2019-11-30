package licos.entity

import licos.knowledge.Alive

import scala.collection.mutable.ListBuffer

final case class VillageInfoWithAvatarList(villageInfo: VillageInfo, avatarList: Seq[AvatarInVillage]) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.MutableDataStructures"))
  def alivePlayerList: Seq[PlayerInVillage] = {
    val playerBuffer = ListBuffer.empty[PlayerInVillage]
    import cats.implicits._
    avatarList foreach {
      case player: PlayerInVillage if player.getStatus(villageInfo.phase, villageInfo.day).label === Alive.label =>
        playerBuffer += player
      case _ =>
    }
    playerBuffer.result
  }

  def numberOfAlivePlayers: Int = alivePlayerList.size
}
