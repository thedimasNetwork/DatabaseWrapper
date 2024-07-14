package stellar.database.types;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UnitSnapshot {
    private final String teamId;
    private final String teamColor;
    private final List<UnitCount> units = new ArrayList<>();
}
