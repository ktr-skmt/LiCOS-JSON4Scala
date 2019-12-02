package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.bson.element.village.role.BsonSimpleRole;
import licos.json.element.village.client2server.JsonOnymousAudienceBoard;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("onymousAudienceBoards")
public class BsonOnymousAudienceBoard extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonAvatar avatar;

    @Getter @Setter @Reference
    private BsonSimpleCharacter character;

    @Getter @Setter @Reference
    private BsonSimpleRole role;

    @Getter @Setter
    private String prediction;

    @SuppressWarnings("unused")
    private BsonOnymousAudienceBoard() {
        // Do nothing
    }

    public BsonOnymousAudienceBoard(ObjectId _id,
                                    BsonBase base,
                                    BsonAvatar avatar,
                                    BsonSimpleCharacter character,
                                    BsonSimpleRole role,
                                    String prediction) {
        this._id = _id;
        this.base = base;
        this.avatar = avatar;
        this.character = character;
        this.role = role;
        this.prediction = prediction;
    }

    @Override
    public JsonOnymousAudienceBoard toJson() {
        return new JsonOnymousAudienceBoard(
                base.toJson(),
                avatar.toJson(),
                character.toJson(),
                role.toJson(),
                prediction
        );
    }
}