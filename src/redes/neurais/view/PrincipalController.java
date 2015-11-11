/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes.neurais.view;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrincipalController implements Initializable {

    @FXML
    private RadioButton rdTanHiperbolica;

    @FXML
    private CategoryAxis iteracaoAxis;

    @FXML
    private TextField txtErro;

    @FXML
    private NumberAxis valorAxis;

    @FXML
    private MenuItem menuTeste;

    @FXML
    private TextField txtSaida;

    @FXML
    private MenuItem menuTreino;

    @FXML
    private TextField txtOculta;

    @FXML
    private Button btnTreinar;

    @FXML
    private Menu menuSair;

    @FXML
    private Label txtTeste;

    @FXML
    private RadioButton rdLinear;

    @FXML
    private TextField txtEntrada;

    @FXML
    private BarChart<Integer, Double> chartTreino;

    @FXML
    private RadioButton rdLogistica;

    @FXML
    private Button btnTestar;

    @FXML
    private Label txtTreino;

    @FXML
    private TextField txtIteracao;

    private PrincipalBean bean;
    private ToggleGroup grupoFuncao;
    private XYChart.Series serie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bean = new PrincipalBean();

        grupoFuncao = new ToggleGroup();
        rdLinear.setToggleGroup(grupoFuncao);
        rdLogistica.setToggleGroup(grupoFuncao);
        rdTanHiperbolica.setToggleGroup(grupoFuncao);

        serie = new XYChart.Series();

        iteracaoAxis.setLabel("Quantidade de Iterações");
        valorAxis.setLabel("Erro Geral da Rede");

        chartTreino.getData().add(serie);
    }

    @FXML
    void clickTreinar(ActionEvent event) {
        Random r = new Random();
        XYChart.Data data = new XYChart.Data("sdfasd", r.nextDouble());
        serie.getData().add(data);
    }

    @FXML
    void clickTestar(ActionEvent event) {

    }

    @FXML
    void clickArquivoTreino(ActionEvent event) {

    }

    @FXML
    void clickArquivoTeste(ActionEvent event) {

    }

    @FXML
    void clickSair(ActionEvent event) {

    }
}
