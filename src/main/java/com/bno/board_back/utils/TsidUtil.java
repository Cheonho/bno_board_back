package com.bno.board_back.utils;

import com.github.f4b6a3.tsid.TsidFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TsidUtil {

    @Value("${env.tsid.tz}")
    private String tz;

    @Value("${env.tsid.nodeBit}")
    private int nodeBit;

    private TsidFactory tsidFactory;

    @PostConstruct
    public void init() {
        Clock clock = Clock.system(ZoneId.of(tz));
        this.tsidFactory = TsidFactory.builder()
                .withClock(clock)
                .withNodeBits(nodeBit)
                .withRandomFunction(() -> ThreadLocalRandom.current().nextInt())
                .build();
    }

    public String getTsid() {
        if (this.tsidFactory == null) {
            init();
        }
        return this.tsidFactory.create().toString();
//        return this.tsidFactory.create().toLong();
    }
}
