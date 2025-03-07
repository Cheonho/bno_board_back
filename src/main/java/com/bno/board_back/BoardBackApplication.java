package com.bno.board_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardBackApplication {

	public static void main(String[] args) {
		System.setProperty("tsid.tz", "Asia/Seoul");
		System.setProperty("tsid.note.bit", "10");
		SpringApplication.run(BoardBackApplication.class, args);
	}

}
