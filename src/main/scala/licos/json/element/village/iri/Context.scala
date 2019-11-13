package licos.json.element.village.iri

import licos.WerewolfWorld

sealed abstract class Context(label: String) {
  def iri: String = WerewolfWorld.context(label)
}

case object BaseContext extends Context("base")
case object ErrorContext extends Context("error")
case object CharacterContext extends Context("character")
case object AvatarContext extends Context("avatar")
case object RoleContext extends Context("role")
case object BoardContext extends Context("board")
case object ChatContext extends Context("chat")
case object VillageContext extends Context("village")
case object VoteContext extends Context("vote")
case object VotingResultContext extends Context("votingResult")
case object ScrollContext extends Context("scroll")
case object FlavorTextContext extends Context("flavorText")
