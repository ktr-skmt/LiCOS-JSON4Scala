package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.json.element.village.JsonBoardResult;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("boardResults")
public class BsonBoardResult extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $context;

    @Getter @Setter
    private String $id;

    @Getter @Setter @Reference
    private BsonSimpleCharacter character;

    @Getter @Setter
    private String polarity;

    @Getter @Setter
    private String phase;

    @Getter @Setter
    private int day;

    @SuppressWarnings("unused")
    private BsonBoardResult() {
        // Do nothing
    }

    public BsonBoardResult(ObjectId _id,
                           String $context,
                           String $id,
                           BsonSimpleCharacter character,
                           String polarity,
                           String phase,
                           int day) {
        this.$context = $context;
        this.$id = $id;
        this._id = _id;
        this.character = character;
        this.polarity = polarity;
        this.phase = phase;
        this.day = day;
    }

    @Override
    public JsonBoardResult toJson() {
        return new JsonBoardResult(
                $context,
                $id,
                character.toJson(),
                polarity,
                phase,
                day
        );
    }
}
