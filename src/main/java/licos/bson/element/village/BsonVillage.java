package licos.bson.element.village;

import licos.json.element.village.JsonVillage;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("villages")
public class BsonVillage extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $context;

    @Getter @Setter
    private String $id;

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private int totalNumberOfCharacters;

    @Getter @Setter
    private String lang;

    @Getter @Setter
    private BsonChatSettings chatSettings;

    @SuppressWarnings("unused")
    private BsonVillage() {
        // Do nothing
    }

    public BsonVillage(ObjectId _id,
                       String $context,
                       String $id,
                       long id,
                       String name,
                       int totalNumberOfCharacters,
                       String lang,
                       BsonChatSettings chatSettings) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.id = id;
        this.name = name;
        this.totalNumberOfCharacters = totalNumberOfCharacters;
        this.lang = lang;
        this.chatSettings = chatSettings;
    }

    @Override
    public JsonVillage toJson() {
        return new JsonVillage(
          $context,
          $id,
          id,
          name,
          totalNumberOfCharacters,
          lang,
          chatSettings.toJson()
        );
    }
}
