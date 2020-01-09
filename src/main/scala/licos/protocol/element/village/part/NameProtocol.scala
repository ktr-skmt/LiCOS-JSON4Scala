package licos.protocol.element.village.part

import java.util.Locale

import licos.json.element.village.JsonName

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
final case class NameProtocol() {
  private var ar_   : String = ""
  private var de_   : String = ""
  private var en_   : String = ""
  private var es_   : String = ""
  private var it_   : String = ""
  private var fr_   : String = ""
  private var ja_   : String = ""
  private var pt_   : String = ""
  private var ru_   : String = ""
  private var uk_   : String = ""
  private var vi_   : String = ""
  private var zhCN_ : String = ""
  private var zhTW_ : String = ""

  def ar(name:   String): NameProtocol = { ar_   = name; this }
  def de(name:   String): NameProtocol = { de_   = name; this }
  def en(name:   String): NameProtocol = { en_   = name; this }
  def es(name:   String): NameProtocol = { es_   = name; this }
  def it(name:   String): NameProtocol = { it_   = name; this }
  def fr(name:   String): NameProtocol = { fr_   = name; this }
  def ja(name:   String): NameProtocol = { ja_   = name; this }
  def pt(name:   String): NameProtocol = { pt_   = name; this }
  def ru(name:   String): NameProtocol = { ru_   = name; this }
  def uk(name:   String): NameProtocol = { uk_   = name; this }
  def vi(name:   String): NameProtocol = { vi_   = name; this }
  def zhCN(name: String): NameProtocol = { zhCN_ = name; this }
  def zhTW(name: String): NameProtocol = { zhTW_ = name; this }

  def ar:   String = ar_
  def de:   String = de_
  def en:   String = en_
  def es:   String = es_
  def it:   String = it_
  def fr:   String = fr_
  def ja:   String = ja_
  def pt:   String = pt_
  def ru:   String = ru_
  def uk:   String = uk_
  def vi:   String = vi_
  def zhCN: String = zhCN_
  def zhTW: String = zhTW_

  def json(localeOpt: Option[Locale]): JsonName = {
    def all: JsonName = {
      JsonName(
        en_,
        Option(ar_),
        Option(de_),
        Option(es_),
        Option(it_),
        Option(fr_),
        Option(ja_),
        Option(pt_),
        Option(ru_),
        Option(uk_),
        Option(vi_),
        Option(zhCN_),
        Option(zhTW_)
      )
    }
    localeOpt match {
      case Some(locale: Locale) =>
        import Locale._
        locale match {
          case ARABIC =>
            JsonName(
              en_,
              Option(ar_),
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
          case GERMAN =>
            JsonName(
              en_,
              Option.empty[String],
              Option(de_),
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
          case SPANISH =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option(es_),
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
          case FRENCH =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option(fr_),
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
          case ITALIAN =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option(it_),
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
          case JAPANESE =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option(ja_),
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
          case PORTUGUESE =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option(pt_),
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
          case RUSSIAN =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option(ru_),
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
          case UKRAINIAN =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option(uk_),
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
          case VIETNAMESE =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option(vi_),
              Option.empty[String],
              Option.empty[String]
            )
          case CHINESE_ =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option(zhCN_),
              Option.empty[String]
            )
          case TAIWANESE =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option(zhTW_)
            )
          case ENGLISH | _ =>
            JsonName(
              en_,
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String],
              Option.empty[String]
            )
        }
      case None =>
        all
    }
  }

  private val ARABIC:     Locale = new Locale("ar")
  private val SPANISH:    Locale = new Locale("es")
  private val PORTUGUESE: Locale = new Locale("pt")
  private val RUSSIAN:    Locale = new Locale("ru")
  private val UKRAINIAN:  Locale = new Locale("uk")
  private val VIETNAMESE: Locale = new Locale("vi")
  private val CHINESE_  : Locale = new Locale("zh-CN")
  private val TAIWANESE:  Locale = new Locale("zh-TW")

  def withLanguage(locale: Locale): String = {
    import Locale._
    locale match {
      case ARABIC     => ar
      case GERMAN     => de
      case ENGLISH    => en
      case SPANISH    => es
      case ITALIAN    => it
      case FRENCH     => fr
      case JAPANESE   => ja
      case PORTUGUESE => pt
      case RUSSIAN    => ru
      case UKRAINIAN  => uk
      case VIETNAMESE => vi
      case CHINESE_   => zhCN
      case TAIWANESE  => zhTW
      case _          => en
    }
  }
}
