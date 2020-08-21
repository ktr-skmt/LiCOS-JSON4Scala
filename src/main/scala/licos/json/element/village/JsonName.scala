package licos.json.element.village

final case class JsonName(
    en:   String,
    ar:   Option[String],
    de:   Option[String],
    es:   Option[String],
    fr:   Option[String],
    it:   Option[String],
    ja:   Option[String],
    pt:   Option[String],
    ru:   Option[String],
    uk:   Option[String],
    vi:   Option[String],
    zhCN: Option[String],
    zhTW: Option[String]
) extends JsonElement {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      en:   String,
      ar:   String,
      de:   String,
      es:   String,
      it:   String,
      fr:   String,
      ja:   String,
      pt:   String,
      ru:   String,
      uk:   String,
      vi:   String,
      zhCN: String,
      zhTW: String
  ) = {
    this(
      en:           String,
      Option(ar):   Option[String],
      Option(de):   Option[String],
      Option(es):   Option[String],
      Option(it):   Option[String],
      Option(fr):   Option[String],
      Option(ja):   Option[String],
      Option(pt):   Option[String],
      Option(ru):   Option[String],
      Option(uk):   Option[String],
      Option(vi):   Option[String],
      Option(zhCN): Option[String],
      Option(zhTW): Option[String]
    )
  }

}

object JsonName {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonName] = (
    (JsPath \ "en").read[String] and
      (JsPath \ "ar").readNullable[String] and
      (JsPath \ "de").readNullable[String] and
      (JsPath \ "es").readNullable[String] and
      (JsPath \ "it").readNullable[String] and
      (JsPath \ "fr").readNullable[String] and
      (JsPath \ "ja").readNullable[String] and
      (JsPath \ "pt").readNullable[String] and
      (JsPath \ "ru").readNullable[String] and
      (JsPath \ "uk").readNullable[String] and
      (JsPath \ "vi").readNullable[String] and
      (JsPath \ "zhCN").readNullable[String] and
      (JsPath \ "zhTW").readNullable[String]
  )(JsonName.apply _)

  implicit val jsonWrites: OWrites[JsonName] = Json.writes[JsonName]
}
