package licos.bson.element.village;

import licos.json.element.village.JsonElement;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

abstract class BsonElementToJsonElement extends BsonElement {
    <T extends BsonElement> Stream<JsonElement> elementToJson(List<T> list) {
        Iterator<T> iterator = list.iterator();
        Stream.Builder<JsonElement> ret = Stream.builder();
        while (iterator.hasNext()) {
            ret.accept(iterator.next().toJson());
        }
        return ret.build();
    }
}
