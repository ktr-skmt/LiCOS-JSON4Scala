package licos.bson.element.village;

import licos.json.element.village.*;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("anonymousAudienceChats")
public class BsonAnonymousAudienceChat extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter
    private boolean isMine;

    @Getter @Setter
    private BsonChatText text;

    @Getter @Setter
    private int maxLengthOfUnicodeCodePoints;

    @Getter @Setter
    private boolean isFromServer;

    @SuppressWarnings("unused")
    private BsonAnonymousAudienceChat() {
        // Do nothing
    }

    public BsonAnonymousAudienceChat(ObjectId _id,
                                     BsonBase base,
                                     boolean isMine,
                                     BsonChatText text,
                                     int maxLengthOfUnicodeCodePoints,
                                     boolean isFromServer) {
        this._id = _id;
        this.base = base;
        this.isMine = isMine;
        this.text = text;
        this.maxLengthOfUnicodeCodePoints = maxLengthOfUnicodeCodePoints;
        this.isFromServer = isFromServer;
    }

    @Override
    public JsonAnonymousAudienceChat toJson() {
        return new JsonAnonymousAudienceChat(
                base.toJson(),
                new JsonSubAnonymousAudienceChat(
                        isMine,
                        text.toJson(),
                        maxLengthOfUnicodeCodePoints,
                        isFromServer
                )
        );
    }
}
