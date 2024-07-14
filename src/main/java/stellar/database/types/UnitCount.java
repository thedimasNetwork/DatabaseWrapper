package stellar.database.types;

import lombok.Data;

@Data
public class UnitCount {
    private final String unitType;
    private final int count;
}
