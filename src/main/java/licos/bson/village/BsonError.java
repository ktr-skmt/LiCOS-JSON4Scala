package licos.bson.village;

import licos.json.village.JsonError;
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
@Entity("errors")
public class BsonError extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonName content;

    @Getter @Setter
    private String severity;

    @Getter @Setter
    private String source;

    @SuppressWarnings("unused")
    private BsonError() {
        // Do nothing
    }

    public BsonError(ObjectId _id,
                     BsonBase base,
                     BsonName content,
                     String severity,
                     String source) {
        this._id = _id;
        this.base = base;
        this.content = content;
        this.severity = severity;
        this.source = source;
    }

    @Override
    public JsonError toJson() {
        return new JsonError(
                base.toJson(),
                content.toJson(),
                severity,
                source
        );
    }
}
