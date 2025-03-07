package com.bno.board_back.dto.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Path {

    private int id;
    private String description;
    private String path;
    private String component;
}
