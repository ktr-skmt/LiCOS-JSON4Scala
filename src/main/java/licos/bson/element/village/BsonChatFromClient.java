package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonRoleCharacter;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.json.element.village.JsonChatFromClient;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("chatsFromClient")
public class BsonChatFromClient extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonRoleCharacter myCharacter;

    @Getter @Setter @Reference
    private BsonSimpleCharacter character;

    @Getter @Setter
    private boolean isMine;

    @Getter @Setter @Reference
    private BsonChatText text;

    @Getter @Setter
    private int maxLengthOfUnicodeCodePoints;

    @Getter @Setter
    private boolean isOver;

    @SuppressWarnings("unused")
    private BsonChatFromClient() {
        // Do nothing
    }

    public BsonChatFromClient(ObjectId _id,
                              BsonBase base,
                              BsonRoleCharacter myCharacter,
                              BsonSimpleCharacter character,
                              boolean isMine,
                              BsonChatText text,
                              int maxLengthOfUnicodeCodePoints,
                              boolean isOver) {
        this._id = _id;
        this.base = base;
        this.myCharacter = myCharacter;
        this.character = character;
        this.isMine = isMine;
        this.text = text;
        this.maxLengthOfUnicodeCodePoints = maxLengthOfUnicodeCodePoints;
        this.isOver = isOver;
    }

    @Override
    public JsonChatFromClient toJson() {
        return new JsonChatFromClient(
                base.toJson(),
                myCharacter.toJson(),
                character.toJson(),
                isMine,
                text.toJson(),
                maxLengthOfUnicodeCodePoints,
                isOver
        );
    }
}
