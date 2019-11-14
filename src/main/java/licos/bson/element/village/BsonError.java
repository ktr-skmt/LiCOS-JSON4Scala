package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.json.element.village.JsonError;
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
@Entity("errors")
public class BsonError extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonName content;

    @Getter @Setter
    private String severity;

    @Getter @Setter
    private String source;

    @Getter @Setter
    private boolean isFromServer;

    @SuppressWarnings("unused")
    private BsonError() {
        // Do nothing
    }

    public BsonError(ObjectId _id,
                     BsonBase base,
                     BsonName content,
                     String severity,
                     String source,
                     boolean isFromServer) {
        this._id = _id;
        this.base = base;
        this.content = content;
        this.severity = severity;
        this.source = source;
        this.isFromServer = isFromServer;
    }

    @Override
    public JsonError toJson() {
        return new JsonError(
                base.toJson(),
                content.toJson(),
                severity,
                source,
                isFromServer
        );
    }
}