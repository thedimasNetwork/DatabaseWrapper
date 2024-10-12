package stellar.database.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import stellar.database.enums.BanType;

@Data
@AllArgsConstructor
public class BanMeta {
    private final BanType type;
    private final int period;
    private final String comment;

    public BanMeta(@NonNull BanType type, int period) {
        this(type, period, null);
    }
}
