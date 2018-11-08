import javafx.collections.ObservableList;
import jdk.jfr.Timestamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Block {

    private ObservableList<Transaction> transactions;
    private BlockHeader blockHeader;

    public Block(ObservableList<Transaction> transactions)
    {
        this.transactions=transactions;
        this.blockHeader=new BlockHeader();
    }

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }
}
