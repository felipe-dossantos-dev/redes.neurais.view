/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes.neurais.view;

import br.fipp.entrada.MatrizConfusao;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author felipe
 */
public class SaidaController implements Initializable {

    @FXML
    private Label lblArquivo;

    @FXML
    private TableView<String[]> tabela;

    private MatrizConfusao matConfusao;
    private String filePath;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public MatrizConfusao getMatConfusao() {
        return matConfusao;
    }

    public void setMatConfusao(MatrizConfusao matConfusao) {
        this.matConfusao = matConfusao;
        TableColumn<String[], String> coluna = new TableColumn<>("Valor / Valor");
        coluna.setMinWidth(55);
        coluna.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> param) -> new SimpleStringProperty(param.getValue()[0]));
        tabela.getColumns().add(coluna);
        //gerar todas as colunas
        Set<String> chaves = matConfusao.getChaves();
        for (String chave : chaves) {
            TableColumn<String[], String> coluna1 = new TableColumn<>(chave);
            final int posCol = matConfusao.getPosicao(chave) + 1;
            coluna1.setCellValueFactory(
                    (TableColumn.CellDataFeatures<String[], String> param) -> 
                            new SimpleStringProperty(param.getValue()[posCol]));
            tabela.getColumns().add(coluna1);
        }

        //dados
        ObservableList<String[]> dados = FXCollections.observableArrayList();
        int[][] matriz = matConfusao.getMatriz();
        for (String valor : matConfusao.getChaves()) {
            int pos = matConfusao.getPosicao(valor);
            int[] linha = matriz[pos];
            String[] row = new String[linha.length + 1];
            row[0] = valor;
            for (int i = 0; i < linha.length; i++) {
                int row1 = linha[i];
                row[i + 1] = Integer.toString(row1);
            }
            dados.add(row);
        }
        tabela.setItems(dados);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        lblArquivo.setText(filePath);
    }
}
