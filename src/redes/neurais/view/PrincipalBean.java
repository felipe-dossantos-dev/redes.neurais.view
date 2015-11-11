/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes.neurais.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PrincipalBean {

    private final StringProperty funcaoSaida;
    private final IntegerProperty iteracao;
    private final DoubleProperty erro;
    private final IntegerProperty camadaEntrada;
    private final IntegerProperty camadaOculta;
    private final IntegerProperty camadaSaida;
    private final DoubleProperty taxaAprendizagem;
    private final DoubleProperty linear;
    
    public PrincipalBean() {
        funcaoSaida = new SimpleStringProperty();
        iteracao = new SimpleIntegerProperty();
        erro = new SimpleDoubleProperty();
        camadaEntrada = new SimpleIntegerProperty();
        camadaOculta = new SimpleIntegerProperty();
        camadaSaida = new SimpleIntegerProperty();
        taxaAprendizagem = new SimpleDoubleProperty();
        linear = new SimpleDoubleProperty();
    }

    public String getFuncaoSaida() {
        return funcaoSaida.get();
    }

    public void setFuncaoSaida(String value) {
        funcaoSaida.set(value);
    }

    public StringProperty funcaoSaidaProperty() {
        return funcaoSaida;
    }

    public int getIteracao() {
        return iteracao.get();
    }

    public void setIteracao(int value) {
        iteracao.set(value);
    }

    public IntegerProperty iteracaoProperty() {
        return iteracao;
    }

    public double getErro() {
        return erro.get();
    }

    public void setErro(double value) {
        erro.set(value);
    }

    public DoubleProperty erroProperty() {
        return erro;
    }

    public int getCamadaEntrada() {
        return camadaEntrada.get();
    }

    public void setCamadaEntrada(int value) {
        camadaEntrada.set(value);
    }

    public IntegerProperty camadaEntradaProperty() {
        return camadaEntrada;
    }

    public int getCamadaOculta() {
        return camadaOculta.get();
    }

    public void setCamadaOculta(int value) {
        camadaOculta.set(value);
    }

    public IntegerProperty camadaOcultaProperty() {
        return camadaOculta;
    }

    public int getCamadaSaida() {
        return camadaSaida.get();
    }

    public void setCamadaSaida(int value) {
        camadaSaida.set(value);
    }

    public IntegerProperty camadaSaidaProperty() {
        return camadaSaida;
    }

    public double getTaxaAprendizagem() {
        return taxaAprendizagem.get();
    }

    public void setTaxaAprendizagem(double value) {
        taxaAprendizagem.set(value);
    }

    public DoubleProperty taxaAprendizagemProperty() {
        return taxaAprendizagem;
    }
    
    public double getLinear() {
        return linear.get();
    }

    public void setLinear(double value) {
        linear.set(value);
    }

    public DoubleProperty linearProperty() {
        return linear;
    }
}
