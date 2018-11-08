import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private ArrayList<Block> blocks=new ArrayList<Block>();
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
        if(numberOfBlock>0)
            currentBlock.getBlockHeader().setPrevBlockHashValue(blocks.get(numberOfBlock-1).getBlockHeader().getHashValue());
        fillBlockHeader(currentBlock);
        this.blocks.add(currentBlock);
        numberOfBlock++;
        try
        {
            VBox newBlock = FXMLLoader.load(getClass().getResource("block.fxml"));
            this.blockLayout.getChildren().add(newBlock);
            ((TitledPane)newBlock.getChildren().get(0)).setText("Block Header "+(numberOfBlock+1));
            this.initTransactionList();
        }catch (Exception e)
        {

        }



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
        byte[] blockHeaderByte=Hash.serializeToByte(block.getBlockHeader());
        byte[] headerHash=hash.hash(new String(blockHeaderByte,2));
        block.getBlockHeader().setHashValue(new String(headerHash,2));
        Label hashValue=(Label)((AnchorPane)((TitledPane)blockHeader.getChildren().get(1)).getContent()).getChildren().get(0);
        hashValue.setText(block.getBlockHeader().getHashValue());
        Label merkleRoot=(Label)((AnchorPane)((TitledPane)blockHeader.getChildren().get(3)).getContent()).getChildren().get(0);
        merkleRoot.setText(block.getBlockHeader().getMerkleRoot());
        Label prevHashBlock=(Label)((AnchorPane)((TitledPane)blockHeader.getChildren().get(2)).getContent()).getChildren().get(0);
        prevHashBlock.setText(block.getBlockHeader().getPrevBlockHashValue());


    }
}
