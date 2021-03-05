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
    update:    UpdateProtocol,
    isAChoice: Boolean
) {

  lazy val json: JsonCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, s"character#${character.getId.toString}")
    JsonCharacter(
      CharacterContext.iri,
      `@id`,
      character.getId,
      character.name.json(Option(language)),
      character.icon,
      isMine,
      status.label,
      update.json(`@id`),
      isAChoice
    )
  }

}
