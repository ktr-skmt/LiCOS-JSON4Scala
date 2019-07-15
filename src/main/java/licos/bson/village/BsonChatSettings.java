package licos.bson.village;

import licos.json.village.JsonChatSettings;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import scala.Option;

public class BsonChatSettings extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $context;

    @Getter @Setter
    private String $id;

    @Getter @Setter
    private int limit;

    @Getter @Setter
    private int characterLimit;

    @SuppressWarnings("unused")
    private BsonChatSettings() {
        // Do nothing
    }

    public BsonChatSettings(ObjectId _id,
                            String $context,
                            String $id,
                            int limit,
                            int characterLimit) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.limit = limit;
        this.characterLimit = characterLimit;
    }

    @Override
    public JsonChatSettings toJson() {
        return new JsonChatSettings(
                Option.apply($context),
                Option.apply($id),
                limit,
                characterLimit
        );
    }
}
