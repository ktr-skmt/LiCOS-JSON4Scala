package licos.bson.element.village;

import licos.json.element.village.JsonOnymousAudienceChat;
import licos.json.element.village.JsonSubOnymousAudienceChat;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("onymousAudienceChats")
public class BsonOnymousAudienceChat extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonAvatar avatar;

    @Getter @Setter
    private boolean isMine;

    @Getter @Setter
    private BsonChatText text;

    @Getter @Setter
    private int maxLengthOfUnicodeCodePoints;

    @Getter @Setter
    private boolean isFromServer;

    @SuppressWarnings("unused")
    private BsonOnymousAudienceChat() {
        // Do nothing
    }

    public BsonOnymousAudienceChat(ObjectId _id,
                                   BsonBase base,
                                   BsonAvatar avatar,
                                   boolean isMine,
                                   BsonChatText text,
                                   int maxLengthOfUnicodeCodePoints,
                                   boolean isFromServer) {
        this._id = _id;
        this.base = base;
        this.avatar = avatar;
        this.isMine = isMine;
        this.text = text;
        this.maxLengthOfUnicodeCodePoints = maxLengthOfUnicodeCodePoints;
        this.isFromServer = isFromServer;
    }

    @Override
    public JsonOnymousAudienceChat toJson() {
        return new JsonOnymousAudienceChat(
                base.toJson(),
                new JsonSubOnymousAudienceChat(
                        avatar.toJson(),
                        isMine,
                        text.toJson(),
                        maxLengthOfUnicodeCodePoints,
                        isFromServer
                )
        );
    }
}