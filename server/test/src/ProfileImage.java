
/**
 * 
 */
public class ProfileImage
    extends AbstractTest
{

    private final static cpi.ProfileLabels ProfileLabels1 = new cpi.ProfileLabels();
    private final static cpi.ProfileLabels ProfileLabels2 = new cpi.ProfileLabels("Friendly Angry Smart Funny");


    public ProfileImage(){
        super();
    }


    public void testProfileImage1(){
        try {
            cpi.ProfileImage image = new cpi.ProfileImage(1.0f,0.5f,0.3f,0.5f,ProfileLabels1);

            java.io.File out = new java.io.File("testProfileImage1.png");

            byte[] png = image.toPNG();
            java.io.OutputStream os = new java.io.FileOutputStream(out);
            try {
                os.write(png,0,png.length);
                os.flush();
            }
            finally {
                os.close();
            }

            assertTrue(true);
        }
        catch (java.io.IOException exc){

            exc.printStackTrace();

            assertTrue(false);
        }
    }
    public void testProfileImage2(){
        try {
            cpi.ProfileImage image = new cpi.ProfileImage(1.0f,0.5f,0.3f,0.5f,ProfileLabels2);

            java.io.File out = new java.io.File("testProfileImage2.png");

            byte[] png = image.toPNG();
            java.io.OutputStream os = new java.io.FileOutputStream(out);
            try {
                os.write(png,0,png.length);
                os.flush();
            }
            finally {
                os.close();
            }

            assertTrue(true);
        }
        catch (java.io.IOException exc){

            exc.printStackTrace();

            assertTrue(false);
        }
    }
}
