package licos.protocol.element.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Status}
import licos.protocol.element.village.part.UpdateProtocol
import licos.util.LiCOSOnline

final case class CharacterProtocol(
    character: Character,
    villageId: Long,
    language:  Locale,
    isMine:    Boolean,
    status:    Status,
    updateProtocol: UpdateProtocol,
    isAChoice: Boolean
) {

  private val `@id`: String = LiCOSOnline.state(villageId, s"character#${character.getId}")

  val json: JsonCharacter = JsonCharacter(
    CharacterContext.iri,
    `@id`,
    character.getId,
    character.name.json(Option(language)),
    character.icon,
    isMine,
    status.label,
    updateProtocol.json(`@id`),
    isAChoice
  )

}
