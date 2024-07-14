package stellar.database.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jooq.Converter;
import org.jooq.JSONB;
import stellar.database.types.UnitSnapshot;

import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
public class UnitSnapshotConverter implements Converter<JSONB, List<UnitSnapshot>> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<UnitSnapshot> from(JSONB databaseObject) {
        try {
            return mapper.readValue(databaseObject.data(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to deserialize UnitSnapshot from JSON", e);
        }
    }

    @Override
    public JSONB to(List<UnitSnapshot> userObject) {
        try {
            return JSONB.valueOf(mapper.writeValueAsString(userObject));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to serialize UnitSnapshot to JSON", e);
        }
    }

    @Override
    @NotNull
    public Class<JSONB> fromType() {
        return JSONB.class;
    }

    @Override
    @NotNull
    public Class<List<UnitSnapshot>> toType() {
        return (Class<List<UnitSnapshot>>) (Class<?>) List.class;
    }
}
