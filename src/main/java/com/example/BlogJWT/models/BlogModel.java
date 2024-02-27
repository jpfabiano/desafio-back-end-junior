package com.example.BlogJWT.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "blogTeste")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogModel{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPostagem;
    private String texto;
    private String dataPostagem;
    private String horaPostagem;
    private boolean statusAtualizada;
    private String usuarioPublicacao;

}
