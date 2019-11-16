package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonRoleCharacter;
import licos.json.element.village.JsonScroll;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("scrolls")
public class BsonScroll extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private BsonRoleCharacter myCharacter;

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
                      BsonRoleCharacter myCharacter,
                      String nodeId,
                      int scrollTop,
                      int scrollHeight,
                      int offsetHeight) {
        this._id = _id;
        this.base = base;
        this.myCharacter = myCharacter;
        this.nodeId = nodeId;
        this.scrollTop = scrollTop;
        this.scrollHeight = scrollHeight;
        this.offsetHeight = offsetHeight;
    }

    @Override
    public JsonScroll toJson() {
        return new JsonScroll(
                base.toJson(),
                myCharacter.toJson(),
                nodeId,
                scrollTop,
                scrollHeight,
                offsetHeight
        );
    }
}