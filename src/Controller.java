import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.util.ArrayList;

public class Controller {

    @FXML
    private TextField transactionTF;
    @FXML
    private Button transactionBtn;
    @FXML
    private Button blockBTN;
    @FXML
    private HBox blockLayout;

    public static Hash hash;

    private TableView<Transaction> currentTransactionList;
    private ObservableList<Transaction> currentTransactions;
    private int numberOfBlock;

    public void addTransaction()
    {
        String transaction=transactionTF.getText();
        this.currentTransactions.add(new Transaction(transaction));
        transactionTF.clear();


    }
    public void createBlock()
    {
        Block currentBlock=new Block(currentTransactions);
        fillBlockHeader(currentBlock);

    }

    public void initApplication()
    {
        this.numberOfBlock=0;
        initTransactionList();
    }
    public void initTransactionList()
    {
        this.currentTransactions= FXCollections.observableArrayList();
        this.currentTransactionList=((TableView<Transaction>)((VBox)this.blockLayout.getChildren().get(numberOfBlock)).getChildren().get(1));
        ((TableColumn)this.currentTransactionList.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<Transaction,String>("name"));
        this.currentTransactionList.setItems(this.currentTransactions);
    }
    public void fillBlockHeader(Block block)
    {
        GridPane blockHeader=(GridPane)((TitledPane)((VBox)this.blockLayout.getChildren().get(numberOfBlock)).getChildren().get(0)).getContent();
        Label timestamp=(Label)((AnchorPane)((TitledPane)blockHeader.getChildren().get(0)).getContent()).getChildren().get(0);
        timestamp.setText(block.getBlockHeader().getTimestamp());
        byte[] blockHeaderByte=block.getBlockHeader().serialize();
        byte[] headerHash=this.hash.hash(new String(blockHeaderByte,2));
        block.getBlockHeader().setHashValue(new String(blockHeaderByte,2));
        Label hashValue=(Label)((AnchorPane)((TitledPane)blockHeader.getChildren().get(1)).getContent()).getChildren().get(0);
        hashValue.setText(block.getBlockHeader().getHashValue());

    }
}
