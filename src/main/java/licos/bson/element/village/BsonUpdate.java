package licos.bson.element.village;

import licos.json.element.village.JsonUpdate;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("updates")
public class BsonUpdate extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $id;

    @Getter @Setter
    private String phase;

    @Getter @Setter
    private int date;

    @SuppressWarnings("unused")
    private BsonUpdate() {
        // Do nothing
    }

    public BsonUpdate(ObjectId _id,
                      String $id,
                      String phase,
                      int date) {
        this._id = _id;
        this.$id = $id;
        this.phase = phase;
        this.date = date;
    }

    @Override
    public JsonUpdate toJson() {
        return new JsonUpdate(
                $id,
                phase,
                date
        );
    }
}
