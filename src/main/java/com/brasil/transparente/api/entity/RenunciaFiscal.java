package com.brasil.transparente.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "renuncia_fiscal")
public class RenunciaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long renunciaFiscalId;
    private String ano;
    private String cnpj;
    private String beneficiario;
    double valorRenunciado;
    private Long unidadeFederativaId;

}
