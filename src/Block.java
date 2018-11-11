import javafx.collections.ObservableList;
import jdk.jfr.Timestamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Block {

    private ObservableList<Transaction> transactions;
    private BlockHeader blockHeader;
    private MerkleTree merkleTree;

    /**
     *
     * @param transactions
     */
    public Block(ObservableList<Transaction> transactions)
    {
        //Associate the created transactions to this block
        this.transactions=transactions;
        //Create the Merkle Tree with these transactions
        this.merkleTree=new MerkleTree(this.transactions);
        //produce the representating hash
        this.merkleTree.merkle_tree();
        //Create the Block header with Merkle Tree Hash
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
