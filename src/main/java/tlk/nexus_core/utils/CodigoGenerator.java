package tlk.nexus_core.utils;

import java.time.ZonedDateTime;

public class CodigoGenerator {
    public static String generate(String unidade) {
        return unidade
                + String.format("%02d", ZonedDateTime.now().getYear() % 100)
                + String.format("%02d", ZonedDateTime.now().getMonthValue())
                + String.format("%02d", ZonedDateTime.now().getDayOfMonth())
                + String.format("%02d", ZonedDateTime.now().getHour())
                + String.format("%02d", ZonedDateTime.now().getMinute())
                + String.format("%02d", ZonedDateTime.now().getSecond())
                + String.valueOf(ZonedDateTime.now().getNano() / 100000000);
    }
}
