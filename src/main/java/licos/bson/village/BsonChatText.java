package licos.bson.village;

import licos.json.village.JsonChatText;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("chatTexts")
public class BsonChatText extends BsonElement {
    @Id @SuppressWarnings("unused")
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