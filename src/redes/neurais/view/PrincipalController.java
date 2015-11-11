/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes.neurais.view;

import br.fipp.funcao.saida.FuncaoLinear;
import br.fipp.funcao.saida.FuncaoLogistica;
import br.fipp.funcao.saida.FuncaoSaida;
import br.fipp.funcao.saida.FuncaoTangenteHiperb;
import br.fipp.entrada.LeitorEntradasCSV;
import br.fipp.rede.RedeNeural;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class PrincipalController implements Initializable {

    @FXML
    private RadioButton rdTanHiperbolica;

    @FXML
    private NumberAxis iteracaoAxis;

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
    private LineChart<Number, Number> chartTreino;

    @FXML
    private RadioButton rdLogistica;

    @FXML
    private Button btnTestar;

    @FXML
    private Label txtTreino;

    @FXML
    private TextField txtIteracao;

    @FXML
    private TextField txtAprendizagem;

    @FXML
    private TextField txtLinear;

    private PrincipalBean bean;
    private ToggleGroup grupoFuncao;
    private XYChart.Series serie;
    private RedeNeural rede;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bean = new PrincipalBean();

        //radios button
        grupoFuncao = new ToggleGroup();
        rdLinear.setToggleGroup(grupoFuncao);
        rdLogistica.setToggleGroup(grupoFuncao);
        rdTanHiperbolica.setToggleGroup(grupoFuncao);
        rdTanHiperbolica.setSelected(true);
        //logica do radio linear
        txtLinear.setDisable(true);

        //graficos
        iteracaoAxis.setLabel("Quantidade de Iterações");
        valorAxis.setLabel("Erro Geral da Rede");
        serie = new XYChart.Series();
        serie.setName("Valor do erro geral");
        chartTreino.getData().add(serie);

        //bindings
        StringConverter integerConverter = new IntegerStringConverter();
        StringConverter doubleConverter = new DoubleStringConverter();
        txtIteracao.textProperty().bindBidirectional(
                bean.iteracaoProperty(), integerConverter);
        txtErro.textProperty().bindBidirectional(
                bean.erroProperty(), doubleConverter);
        txtEntrada.textProperty().bindBidirectional(
                bean.camadaEntradaProperty(), integerConverter);
        txtOculta.textProperty().bindBidirectional(
                bean.camadaOcultaProperty(), integerConverter);
        txtSaida.textProperty().bindBidirectional(
                bean.camadaSaidaProperty(), integerConverter);
        txtAprendizagem.textProperty().bindBidirectional(
                bean.taxaAprendizagemProperty(), doubleConverter);
        txtLinear.textProperty().bindBidirectional(
                bean.linearProperty(), doubleConverter);
    }

    @FXML
    void clickTreinar(ActionEvent event) {
        serie.getData().clear();
        //escolha do tipo de funcao de saida
        FuncaoSaida fs = null;
        if (rdLinear.isSelected()) {
            fs = new FuncaoLinear(bean.getLinear());
        } else if (rdLogistica.isSelected()) {
            fs = new FuncaoLogistica();
        } else if (rdTanHiperbolica.isSelected()) {
            fs = new FuncaoTangenteHiperb();
        }
        //recuperar todos os valores
        rede = new RedeNeural(bean.getCamadaEntrada(),
                bean.getCamadaSaida(),
                bean.getCamadaOculta(),
                bean.getErro(),
                bean.getTaxaAprendizagem(),
                bean.getIteracao(),
                new LeitorEntradasCSV(txtTreino.getText()),
                fs);

        try {
            rede.treinar();
        } catch (IOException ex) {
            dialogErro(ex);
        }

        List<Double> lista = rede.getListaSomaErros();

        Platform.runLater(() -> {
            for (int i = 0; i < lista.size(); i++) {
                double atual = lista.get(i);
                serie.getData().add(new XYChart.Data<>(i, atual));
            }
        });
    }

    @FXML
    void clickTestar(ActionEvent event) {
        try {
            LeitorEntradasCSV le = new LeitorEntradasCSV(txtTeste.getText());
            this.rede.avaliar(le);
        } catch (Exception ex) {
            dialogErro(ex);
        }
    }

    @FXML
    void clickArquivoTreino(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        File arq = fileChooser.showOpenDialog(new Stage());
        if (arq != null) {
            txtTreino.setText(arq.getAbsolutePath());
        }
    }

    @FXML
    void clickArquivoTeste(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        File arq = fileChooser.showOpenDialog(new Stage());
        if (arq != null) {
            txtTeste.setText(arq.getAbsolutePath());
        }
    }

    @FXML
    void clickSair(ActionEvent event) {
        this.btnTestar.getScene().getWindow().hide();
        Platform.exit();
    }

    @FXML
    void clickLinear(ActionEvent event) {
        if (rdLinear.isSelected()) {
            this.txtLinear.setDisable(false);
        } else {
            this.txtLinear.setDisable(true);
        }
    }

    private void dialogErro(Exception ex) {
        Alert d = new Alert(AlertType.ERROR);
        d.setHeaderText("Erro!");
        d.setContentText(ex.toString());
        d.showAndWait();
        Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
