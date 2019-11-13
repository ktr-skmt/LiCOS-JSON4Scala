package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import licos.json.element.village.JsonAvatar;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("avatars")
public class BsonAvatar extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $context;

    @Getter @Setter
    private String $id;

    @Getter @Setter
    private String token;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String image;

    @SuppressWarnings("unused")
    private BsonAvatar() {
        // Do nothing
    }

    public BsonAvatar(ObjectId _id,
                      String $context,
                      String $id,
                      String token,
                      String name,
                      String image) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.token = token;
        this.name = name;
        this.image = image;
    }

    @Override
    public JsonAvatar toJson() {
        return new JsonAvatar(
                $context,
                $id,
                token,
                name,
                image
        );
    }
}
