import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlockHeader implements Serializable {

    private String timestamp;
    private String hashValue;
    private String prevBlockHashValue;

    public BlockHeader()
    {
        this.timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

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

    public byte[] serialize()
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {

            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            byte[] byteBlockHeader = bos.toByteArray();
            return byteBlockHeader;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

            try
            {
                bos.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
