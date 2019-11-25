package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonRoleCharacter;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.json.element.village.JsonVote;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("votes")
public class BsonVote extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonRoleCharacter myCharacter;

    @Getter @Setter @Reference
    private BsonSimpleCharacter character;

    @SuppressWarnings("unused")
    private BsonVote() {
        // Do nothing
    }

    public BsonVote(ObjectId _id,
                    BsonBase base,
                    BsonRoleCharacter myCharacter,
                    BsonSimpleCharacter character) {
        this._id = _id;
        this.base = base;
        this.myCharacter = myCharacter;
        this.character = character;
    }

    @Override
    public JsonVote toJson() {
        return new JsonVote(
                base.toJson(),
                myCharacter.toJson(),
                character.toJson()
        );
    }
}
