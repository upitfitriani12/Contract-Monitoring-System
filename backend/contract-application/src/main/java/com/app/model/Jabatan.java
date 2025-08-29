package com.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jabatan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jabatan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJabatan;

    @Column(nullable = false, unique = true)
    private String namaJabatan;
}

