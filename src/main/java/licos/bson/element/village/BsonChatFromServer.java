package licos.bson.element.village;

import licos.bson.element.village.agent.BsonSimpleAgent;
import licos.json.element.village.JsonChatFromServer;
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
@Entity("chatsFromServer")
public class BsonChatFromServer extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonSimpleAgent agent;

    @Getter @Setter
    private boolean isMine;

    @Getter @Setter
    private int id;

    @Getter @Setter
    private int counter;

    @Getter @Setter
    private int limit;

    @Getter @Setter
    private String interval;

    @Getter @Setter @Reference
    private BsonChatText text;

    @Getter @Setter
    private int characterLimit;

    @Getter @Setter
    private boolean isOver;

    @SuppressWarnings("unused")
    private BsonChatFromServer() {
        // Do nothing
    }

    public BsonChatFromServer(ObjectId _id,
                              BsonBase base,
                              BsonSimpleAgent agent,
                              boolean isMine,
                              int id,
                              int counter,
                              int limit,
                              String interval,
                              BsonChatText text,
                              int characterLimit,
                              boolean isOver) {
        this._id = _id;
        this.base = base;
        this.agent = agent;
        this.isMine = isMine;
        this.id = id;
        this.counter = counter;
        this.limit = limit;
        this.interval = interval;
        this.text = text;
        this.characterLimit = characterLimit;
        this.isOver = isOver;
    }

    @Override
    public JsonChatFromServer toJson() {
        return new JsonChatFromServer(
                base.toJson(),
                agent.toJson(),
                isMine,
                id,
                counter,
                limit,
                interval,
                text.toJson(),
                characterLimit,
                isOver
        );
    }
}
