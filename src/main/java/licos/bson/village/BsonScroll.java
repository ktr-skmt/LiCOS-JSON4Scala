package licos.bson.village;

import licos.json.village.JsonScroll;
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
@Entity("scrolls")
public class BsonScroll extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter
    private String nodeId;

    @Getter @Setter
    private int scrollTop;

    @Getter @Setter
    private int scrollHeight;

    @Getter @Setter
    private int offsetHeight;

    @SuppressWarnings("unused")
    private BsonScroll() {
        // Do nothing
    }

    public BsonScroll(ObjectId _id,
                      BsonBase base,
                      String nodeId,
                      int scrollTop,
                      int scrollHeight,
                      int offsetHeight) {
        this._id = _id;
        this.base = base;
        this.nodeId = nodeId;
        this.scrollTop = scrollTop;
        this.scrollHeight = scrollHeight;
        this.offsetHeight = offsetHeight;
    }

    @Override
    public JsonScroll toJson() {
        return new JsonScroll(
                base.toJson(),
                nodeId,
                scrollTop,
                scrollHeight,
                offsetHeight
        );
    }
}
