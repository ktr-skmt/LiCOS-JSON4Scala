package licos.bson.element.village;

import licos.bson.element.village.character.BsonRoleCharacter;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.bson.element.village.role.BsonSimpleRole;
import licos.json.element.village.JsonBoard;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("boards")
public class BsonBoard extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonRoleCharacter myCharacter;

    @Getter @Setter @Reference
    private BsonSimpleCharacter character;

    @Getter @Setter @Reference
    private BsonSimpleRole role;

    @Getter @Setter
    private String prediction;

    @SuppressWarnings("unused")
    private BsonBoard() {
        // Do nothing
    }

    public BsonBoard(ObjectId _id,
                     BsonBase base,
                     BsonRoleCharacter myCharacter,
                     BsonSimpleCharacter character,
                     BsonSimpleRole role,
                     String prediction) {
        this._id = _id;
        this.base = base;
        this.myCharacter = myCharacter;
        this.character = character;
        this.role = role;
        this.prediction = prediction;
    }

    @Override
    public JsonBoard toJson() {
        return new JsonBoard(
                base.toJson(),
                myCharacter.toJson(),
                character.toJson(),
                role.toJson(),
                prediction
        );
    }
}
