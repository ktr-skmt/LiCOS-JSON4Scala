package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.json.element.village.JsonChatFromServer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("chatsFromServer")
public class BsonChatFromServer extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonSimpleCharacter character;

    @Getter @Setter
    private boolean isMine;

    @Getter @Setter
    private int id;

    @Getter @Setter
    private int counter;

    @Getter @Setter
    private int maxNumberOfChatMessages;

    @Getter @Setter
    private String interval;

    @Getter @Setter @Reference
    private BsonChatText text;

    @Getter @Setter
    private int maxLengthOfUnicodeCodePoints;

    @Getter @Setter
    private boolean isOver;

    @SuppressWarnings("unused")
    private BsonChatFromServer() {
        // Do nothing
    }

    public BsonChatFromServer(ObjectId _id,
                              BsonBase base,
                              BsonSimpleCharacter character,
                              boolean isMine,
                              int id,
                              int counter,
                              int maxNumberOfChatMessages,
                              String interval,
                              BsonChatText text,
                              int maxLengthOfUnicodeCodePoints,
                              boolean isOver) {
        this._id = _id;
        this.base = base;
        this.character = character;
        this.isMine = isMine;
        this.id = id;
        this.counter = counter;
        this.maxNumberOfChatMessages = maxNumberOfChatMessages;
        this.interval = interval;
        this.text = text;
        this.maxLengthOfUnicodeCodePoints = maxLengthOfUnicodeCodePoints;
        this.isOver = isOver;
    }

    @Override
    public JsonChatFromServer toJson() {
        return new JsonChatFromServer(
                base.toJson(),
                character.toJson(),
                isMine,
                id,
                counter,
                maxNumberOfChatMessages,
                interval,
                text.toJson(),
                maxLengthOfUnicodeCodePoints,
                isOver
        );
    }
}
