package licos.bson.element.village.character;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.BsonUpdate;
import licos.bson.element.village.BsonName;
import licos.json.element.village.character.JsonCharacter;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("characters")
public class BsonCharacter extends BsonAbstractCharacter {

    @Getter @Setter
    private boolean isMine;

    @Getter @Setter
    private String status;

    @Getter @Setter @Reference
    private BsonUpdate update;

    @Getter @Setter
    private boolean isAChoice;

    @SuppressWarnings("unused")
    private BsonCharacter() {
        // Do nothing
    }

    public BsonCharacter(ObjectId _id,
                         String $context,
                         String $id,
                         int id,
                         BsonName name,
                         String image,
                         boolean isMine,
                         String status,
                         BsonUpdate update,
                         boolean isAChoice) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.id = id;
        this.name = name;
        this.image = image;
        this.isMine = isMine;
        this.status = status;
        this.update = update;
        this.isAChoice = isAChoice;
    }

    @Override
    public JsonCharacter toJson() {
        return new JsonCharacter(
                $context,
                $id,
                id,
                name.toJson(),
                image,
                isMine,
                status,
                update.toJson(),
                isAChoice
        );
    }
}
