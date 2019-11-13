package licos.bson.element.village.character;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.BsonName;
import licos.bson.element.village.role.BsonSimpleRole;
import licos.json.element.village.character.JsonStatusCharacter;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("statusCharacters")
public class BsonStatusCharacter extends BsonAbstractCharacter {

    @Getter @Setter @Reference
    private BsonSimpleRole role;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private boolean isHumanPlayer;
    //private BsonAvatar avatar;

    @SuppressWarnings("unused")
    private BsonStatusCharacter() {
        // Do nothing
    }

    public BsonStatusCharacter(ObjectId _id,
                               String $context,
                               String $id,
                               int id,
                               BsonName name,
                               String image,
                               BsonSimpleRole role,
                               String status,
                               boolean isHumanPlayer) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.id = id;
        this.name = name;
        this.image = image;
        this.role = role;
        this.status = status;
        this.isHumanPlayer = isHumanPlayer;
        //this.avatar = avatar;
    }

    @Override
    public JsonStatusCharacter toJson() {
        return new JsonStatusCharacter(
                $context,
                $id,
                id,
                name.toJson(),
                image,
                role.toJson(),
                status,
                isHumanPlayer
                //avatar.toJson()
        );
    }
}