import javafx.collections.ObservableList;
import jdk.jfr.Timestamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Block {

    private ObservableList<Transaction> transactions;
    private BlockHeader blockHeader;
    private MerkleTree merkleTree;


    public Block(ObservableList<Transaction> transactions)
    {
        this.transactions=transactions;
        this.merkleTree=new MerkleTree(this.transactions);
        this.merkleTree.merkle_tree();
        this.blockHeader=new BlockHeader(this.merkleTree.getRoot());
    }

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }

    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ObservableList<Transaction> transactions) {
        this.transactions = transactions;
    }
    public MerkleTree getMerkleTree() {
        return merkleTree;
    }

    public void setMerkleTree(MerkleTree merkleTree) {
        this.merkleTree = merkleTree;
    }
}
