import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Hash {

    private Cipher aes;
    public static final String  SECRET_KEY ="16BYTESSECRETKEY";
    public static final int BLOCK_SIZE=16;
    public static final String CIPHER_ALGO="AES";

    /**
     * Get the AES Cipher instance
     */
    public Hash()
    {
        try {
            this.aes = Cipher.getInstance(CIPHER_ALGO);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param textToHash
     * @return
     * Method which hash a message
     */
    public byte[] hash(String textToHash)
    {

        byte[] paddingByte;
        try {
            byte[] textByte = null;
            textByte = textToHash.getBytes("UTF-8");
            int paddingLength=textByte.length%BLOCK_SIZE;
            //Add the needed blocks for the message that its length is a multiple of 16 (bytes)
            if(paddingLength!=0)
            {
                paddingByte=bitPadding(paddingLength);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
                outputStream.write( textByte );
                outputStream.write( paddingByte );
                textByte= outputStream.toByteArray( );
            }
            int textByteLength=textByte.length;
            int numberBlocks=textByteLength/BLOCK_SIZE;
            int numBlock=0;
            byte[] hash=SECRET_KEY.getBytes("UTF-8");
            //Compress each block
            for(int i=0;i<numberBlocks;i++)
            {
                byte[] blockToCompress=new byte[BLOCK_SIZE];
                System.arraycopy( textByte, BLOCK_SIZE*i, blockToCompress, 0, BLOCK_SIZE );
                hash=compression(blockToCompress,hash);

            }
            return hash;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param nByte
     * @return
     * Padding method which add nByte-1 bytes filled by zeros and a byte filled with the nByte number
     */
    public byte[] bitPadding(int nByte)
    {
        if(nByte<=0)
            return null;
        byte[] paddingByte=new byte[nByte];
        for(int i=0;i<nByte-1;i++)
        {
            paddingByte[i]=Byte.parseByte("00000000", 2);
        }
        paddingByte[nByte-1]=Byte.parseByte(Integer.toBinaryString(nByte), 2);
        return paddingByte;
    }

    /**
     *
     * @param blockToCompress
     * @param key
     * @return
     * Compress a block of 16 Bytes with the AES Cipher
     */
    public byte[] compression(byte[] blockToCompress,byte[] key)
    {
        try
        {

            this.aes.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, CIPHER_ALGO));
            byte[] compressedValue=this.aes.doFinal(blockToCompress);
            byte[] hashValue=new byte[BLOCK_SIZE];
            for(int i=0;i<BLOCK_SIZE;i++)
            {
                hashValue[i]=(byte)(compressedValue[i]^blockToCompress[i]);
                hashValue[i]^=key[i];
            }
            return hashValue;
        }catch (Exception e)
        {

        }
        return null;
    }

    /**
     *
     * @param object
     * @return
     * Serialize an object to hash it
     */
     public static byte[] serializeToByte(Object object)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {

            out = new ObjectOutputStream(bos);
            out.writeObject(object);
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
