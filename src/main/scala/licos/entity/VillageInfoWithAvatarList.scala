package licos.entity

import licos.knowledge.Alive

final case class VillageInfoWithAvatarList(villageInfo: VillageInfo, avatarList: Seq[AvatarInVillage]) {

  def alivePlayerList: Seq[PlayerInVillage] = {
    import cats.implicits._
    avatarList
      .filter(_.isInstanceOf[PlayerInVillage])
      .map(_.asInstanceOf[PlayerInVillage])
      .filter(_.getStatus(villageInfo.phase, villageInfo.day).label === Alive.label)
  }

  def numberOfAlivePlayers: Int = alivePlayerList.size
}
