package com.wine.api.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.wine.api.Model.CodigoLoja;

@Entity
public class ZipcodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODIGO_LOJA")
    @Enumerated(EnumType.STRING)
    private CodigoLoja codigoDaLoja;

    @Column(name = "FAIXA_INICIO")
    @OrderBy("FAIXA_INICIO")
    @Min(0)
    @Max(99999999)
    private int faixaInicio;

    @Min(0)
    @Max(99999999)
    @Column(name = "FAIXA_FIM")
    private int faixaFim;

    public ZipcodeEntity() {
    }

    public ZipcodeEntity(@NotEmpty @NotNull CodigoLoja codigoDaLoja, int faixaInicio, int faixaFim) {
	super();
	this.codigoDaLoja = codigoDaLoja;
	this.faixaInicio = faixaInicio;
	this.faixaFim = faixaFim;
    }

    public CodigoLoja getCodigoDaLoja() {
	return codigoDaLoja;
    }

    public void setCodigoDaLoja(CodigoLoja codigoDaLoja) {
	this.codigoDaLoja = codigoDaLoja;
    }

    public int getFaixaInicio() {
	return faixaInicio;
    }

    public void setFaixaInicio(int faixaInicio) {

	this.faixaInicio = faixaInicio;

    }

    public int getFaixaFim() {

	return faixaFim;
    }

    public void setFaixaFim(int faixaFim) {

	this.faixaFim = faixaFim;
    }

    public Long getId() {
	return id;
    }

}
