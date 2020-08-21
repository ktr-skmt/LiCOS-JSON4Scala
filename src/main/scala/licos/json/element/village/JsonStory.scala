package licos.json.element.village

import play.api.libs.json.{Json, OFormat}

final case class JsonStory(prologue: Seq[JsonStoryChat], epilogue: JsonStoryEndings, randomText: Seq[JsonStoryChat])

object JsonStory {
  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: OFormat[JsonStory] = Json.format[JsonStory]
}

final case class JsonStoryChat(id: Int, avatar: String, text: JsonStoryText)

object JsonStoryChat {
  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: OFormat[JsonStoryChat] = Json.format[JsonStoryChat]
}

final case class JsonStoryText(en: String, ja: String)

object JsonStoryText {
  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: OFormat[JsonStoryText] = Json.format[JsonStoryText]
}

final case class JsonStoryEndings(
    werewolfEnding:    Seq[JsonStoryChat],
    humanEnding:       Seq[JsonStoryChat],
    werehamsterEnding: Seq[JsonStoryChat]
)

object JsonStoryEndings {
  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: OFormat[JsonStoryEndings] = Json.format[JsonStoryEndings]
}
