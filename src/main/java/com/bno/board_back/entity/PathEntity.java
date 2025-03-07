package com.bno.board_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="path")
@Table(name="routes")
public class PathEntity {

    @Id
    private int id;
    private String description;
    private String path;
    private String component;
}
