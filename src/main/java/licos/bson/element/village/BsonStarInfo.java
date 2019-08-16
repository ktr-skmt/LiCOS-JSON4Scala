package licos.bson.element.village;

import licos.json.element.village.JsonStarInfo;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("starsInfo")
public class BsonStarInfo extends BsonElement {

    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $context;

    @Getter @Setter
    private String $id;

    @Getter @Setter
    private String token;

    @Getter @Setter
    private String serverTimestamp;

    @Getter @Setter
    private String clientTimestamp;

    @Getter @Setter
    private boolean isMarked;

    @SuppressWarnings("unused")
    private BsonStarInfo() {
        // Do nothing
    }

    public BsonStarInfo(ObjectId _id,
                        String $context,
                        String $id,
                        String token,
                        String serverTimestamp,
                        String clientTimestamp,
                        boolean isMarked) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.token = token;
        this.serverTimestamp = serverTimestamp;
        this.clientTimestamp = clientTimestamp;
        this.isMarked = isMarked;
    }

    @Override
    public JsonStarInfo toJson() {
        return new JsonStarInfo(
                $context,
                $id,
                token,
                serverTimestamp,
                clientTimestamp,
                isMarked
        );
    }
}
