package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import licos.json.element.village.JsonName;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("names")
public class BsonName extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String en;

    @Getter @Setter
    private String ar;

    @Getter @Setter
    private String de;

    @Getter @Setter
    private String es;

    @Getter @Setter
    private String it;

    @Getter @Setter
    private String fr;

    @Getter @Setter
    private String ja;

    @Getter @Setter
    private String pt;

    @Getter @Setter
    private String ru;

    @Getter @Setter
    private String uk;

    @Getter @Setter
    private String vi;

    @Getter @Setter
    private String zhCN;

    @Getter @Setter
    private String zhTW;

    @SuppressWarnings("unused")
    private BsonName() {
        // Do nothing
    }

    public BsonName(ObjectId _id,
                    String en,
                    String ar,
                    String de,
                    String es,
                    String it,
                    String fr,
                    String ja,
                    String pt,
                    String ru,
                    String uk,
                    String vi,
                    String zhCN,
                    String zhTW) {
        this._id = _id;
        this.en = en;
        this.ar = ar;
        this.de = de;
        this.es = es;
        this.it = it;
        this.fr = fr;
        this.ja = ja;
        this.pt = pt;
        this.ru = ru;
        this.uk = uk;
        this.vi = vi;
        this.zhCN = zhCN;
        this.zhTW = zhTW;
    }

    @Override
    public JsonName toJson() {
        return new JsonName(
                en,
                ar,
                de,
                es,
                it,
                fr,
                ja,
                pt,
                ru,
                uk,
                vi,
                zhCN,
                zhTW
        );
    }
}
