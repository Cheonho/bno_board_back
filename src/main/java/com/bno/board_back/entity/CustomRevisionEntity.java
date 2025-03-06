package com.bno.board_back.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Entity
@RevisionEntity
@Table(name="RevisionHistory")
public class CustomRevisionEntity {

    @Id
    @Column
    @RevisionNumber
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revisionId;

    @Column @RevisionTimestamp
    private Long updatedAt;

    public LocalDateTime getUpdatedAt() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(updatedAt), ZoneId.systemDefault());
    }
}
