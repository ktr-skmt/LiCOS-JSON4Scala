package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import licos.json.element.village.JsonChatText;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("chatTexts")
public class BsonChatText extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $value;

    @Getter @Setter
    private String $language;

    @SuppressWarnings("unused")
    private BsonChatText() {
        // Do nothing
    }

    public BsonChatText(ObjectId _id,
                        String $value,
                        String $language) {
        this._id = _id;
        this.$value = $value;
        this.$language = $language;
    }

    @Override
    public JsonChatText toJson() {
        return new JsonChatText(
          $value,
          $language
        );
    }
}