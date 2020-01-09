package licos.protocol.element.lobby.part

import licos.json.element.lobby.JsonRoleSetting

final case class RoleSettingProtocol(
    villager:    Int,
    werewolf:    Int,
    seer:        Int,
    medium:      Int,
    madman:      Int,
    hunter:      Int,
    mason:       Int,
    werehamster: Int
) {

  lazy val json: Option[JsonRoleSetting] = {
    Some(
      JsonRoleSetting(
        villager,
        werewolf,
        seer,
        medium,
        madman,
        hunter,
        mason,
        werehamster
      )
    )
  }

}

object RoleSettingProtocol {
  def read(json: JsonRoleSetting): Option[RoleSettingProtocol] = {
    Some(
      RoleSettingProtocol(
        json.villager,
        json.werewolf,
        json.seer,
        json.medium,
        json.madman,
        json.hunter,
        json.mason,
        json.werehamster
      )
    )
  }
}
