import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlockHeader implements Serializable {

    private String timestamp;
    private String hashValue;
    private String prevBlockHashValue;
    private String merkleRoot;
    public BlockHeader(String merkleRoot)
    {
        this.timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        this.merkleRoot=merkleRoot;


    }
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hasValue) {
        this.hashValue = hasValue;
    }

    public String getPrevBlockHashValue() {
        return prevBlockHashValue;
    }

    public void setPrevBlockHashValue(String prevBlockHashValue) {
        this.prevBlockHashValue = prevBlockHashValue;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }
}
