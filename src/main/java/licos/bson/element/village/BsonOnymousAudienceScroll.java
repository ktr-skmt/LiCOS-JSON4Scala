package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.json.element.village.JsonOnymousAudienceScroll;
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
@Entity("onymousAudienceScrolls")
public class BsonOnymousAudienceScroll extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonAvatar avatar;

    @Getter @Setter
    private String nodeId;

    @Getter @Setter
    private int scrollTop;

    @Getter @Setter
    private int scrollHeight;

    @Getter @Setter
    private int offsetHeight;

    @SuppressWarnings("unused")
    private BsonOnymousAudienceScroll() {
        // Do nothing
    }

    public BsonOnymousAudienceScroll(ObjectId _id,
                                     BsonBase base,
                                     BsonAvatar avatar,
                                     String nodeId,
                                     int scrollTop,
                                     int scrollHeight,
                                     int offsetHeight) {
        this._id = _id;
        this.base = base;
        this.avatar = avatar;
        this.nodeId = nodeId;
        this.scrollTop = scrollTop;
        this.scrollHeight = scrollHeight;
        this.offsetHeight = offsetHeight;
    }

    @Override
    public JsonOnymousAudienceScroll toJson() {
        return new JsonOnymousAudienceScroll(
                base.toJson(),
                avatar.toJson(),
                nodeId,
                scrollTop,
                scrollHeight,
                offsetHeight
        );
    }
}
