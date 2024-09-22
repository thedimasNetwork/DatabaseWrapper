package stellar.database.enums;


import lombok.Getter;

import java.time.OffsetDateTime;

public enum BanType {
    teaming(14),
    afk(3),
    ban_evasion(-1),
    freekick(30),
    griefing(30),
    overloading(21),
    spam(7),
    nsfw(30),
    toxicity(30),
    cheating(30),
    impersonation(90),
    inappropriate_name(7),
    other(0); // duration is unused here

    BanType(int duration) {
        this.duration = duration;
    }

    @Getter
    private final int duration; // in days; -1 means permanent
}
