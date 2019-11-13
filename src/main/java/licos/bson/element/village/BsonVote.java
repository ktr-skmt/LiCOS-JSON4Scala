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

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("votes")
public class BsonVote extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonSimpleCharacter character;

    @Getter @Setter @Reference
    private BsonRoleCharacter myCharacter;

    @SuppressWarnings("unused")
    private BsonVote() {
        // Do nothing
    }

    public BsonVote(ObjectId _id,
                    BsonBase base,
                    BsonSimpleCharacter character,
                    BsonRoleCharacter myCharacter) {
        this._id = _id;
        this.base = base;
        this.character = character;
        this.myCharacter = myCharacter;
    }

    @Override
    public JsonVote toJson() {
        return new JsonVote(
                base.toJson(),
                character.toJson(),
                myCharacter.toJson()
        );
    }
}
