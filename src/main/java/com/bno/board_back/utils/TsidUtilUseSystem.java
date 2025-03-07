package com.bno.board_back.utils;

import com.github.f4b6a3.tsid.TsidFactory;

import java.time.Clock;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

public class TsidUtilUseSystem {

    public static final String TSID_TZ = "tsid.tz";
    public static final String TSID_NOTE_BIT = "tsid.note.bit";

    private static final TsidFactory TSID_FACTORY;

    static {
        String tz = System.getProperty(TSID_TZ);
        int  nodeBits = Integer.parseInt(System.getProperty(TSID_NOTE_BIT));

        System.out.println(tz);
        System.out.println(nodeBits);
        Clock clock = Clock.system(ZoneId.of(tz));
        TSID_FACTORY = TsidFactory.builder()
                .withClock(clock)
                .withNodeBits(nodeBits)
                .withRandomFunction(() -> ThreadLocalRandom.current().nextInt())
                .build();
    }

    public static Long getTsid() {
        return TSID_FACTORY.create().toLong();
    }
}
