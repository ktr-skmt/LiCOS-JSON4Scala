package licos.bson.element.village;

import licos.json.element.village.JsonAudienceChat;
import licos.json.element.village.JsonAvatar;
import licos.json.element.village.JsonSubAudienceChat;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import scala.Option;

@Entity("audienceChats")
public class BsonAudienceChat extends BsonElement {
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
    private int characterLimit;

    @Getter @Setter
    private boolean isFromServer;

    @SuppressWarnings("unused")
    private BsonAudienceChat() {
        // Do nothing
    }

    public BsonAudienceChat(ObjectId _id,
                            BsonBase base,
                            Option<BsonAvatar> avatar,
                            boolean isMine,
                            BsonChatText text,
                            int characterLimit,
                            boolean isFromServer) {
        this._id = _id;
        this.base = base;
        if (avatar.nonEmpty()) {
            this.avatar = avatar.get();
        }
        this.isMine = isMine;
        this.text = text;
        this.characterLimit = characterLimit;
        this.isFromServer = isFromServer;
    }

    private Option<JsonAvatar> jsonAvatar() {
        if (avatar == null) {
            return Option.empty();
        } else {
            return Option.apply(avatar.toJson());
        }
    }

    @Override
    public JsonAudienceChat toJson() {
        return new JsonAudienceChat(
                base.toJson(),
                new JsonSubAudienceChat(
                        jsonAvatar(),
                        isMine,
                        text.toJson(),
                        characterLimit,
                        isFromServer
                )
        );
    }
}
