package com.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cabang")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cabang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCabang;

    @Column(nullable = false, unique = true)
    private String namaCabang;
}

