
/**
 * Run the test once, copy output to input, add an instance field, run again.
 * 
 * Confirms that instance fields may be added to classes serialized
 * into the data store.
 */
public class Serialization
    extends AbstractTest
{


    public Serialization(){
        super();
    }

    public void testWriteSerialization(){
        try {
            cpi.ProfileLabels source = new cpi.ProfileLabels();

            Write( gap.Strings.SerializableToString(source), new java.io.File("Serialization.out"));

            assertTrue(true);
        }
        catch (Exception exc){

            exc.printStackTrace();

            assertTrue(false);
        }
    }
    public void testReadSerialization(){
        java.io.File fin = new java.io.File("Serialization.in");
        if (fin.isFile()){
            try {
                String source = Read(fin);

                cpi.ProfileLabels target = (cpi.ProfileLabels)gap.Strings.SerializableFromString(source);

                System.err.println(target);

                assertTrue(true);
            }
            catch (Exception exc){

                exc.printStackTrace();

                assertTrue(false);
            }
        }
        else
            assertTrue(true);
    }

    private static void Write(String string, java.io.File out)
        throws java.io.IOException
    {
        byte[] b64 = string.getBytes();
        java.io.OutputStream os = new java.io.FileOutputStream(out);
        try {
            os.write(b64,0,b64.length);
            os.flush();
        }
        finally {
            os.close();
        }
    }
    private static String Read(java.io.File in)
        throws java.io.IOException
    {
        final int len = (int)in.length();
        byte[] buf = new byte[len];
        java.io.InputStream is = new java.io.FileInputStream(in);
        try {
            int ofs = 0, rem = len, read;
            while (0 < rem){
                read = is.read(buf,ofs,rem);
                rem -= read;
                ofs += read;
            }
        }
        finally {
            is.close();
        }
        return new String(buf,0,0,len);
    }
}

