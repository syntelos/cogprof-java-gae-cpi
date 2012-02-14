
/**
 * 
 */
public class ProfileImage
    extends AbstractTest
{

    private final static cpi.ProfileLabels ProfileLabels1 = new cpi.ProfileLabels();
    private final static cpi.ProfileLabels ProfileLabels2 = new cpi.ProfileLabels("Friendly Sporty Smart Funny");
    private final static cpi.ProfileLabels ProfileLabels3 = new cpi.ProfileLabels("The+Storyteller The+Organizer The+Creative The+Problem+Solver");

    private final static String ProfileFontLat15 = "Lat15-Terminus12x6.psfu";


    public ProfileImage(){
        super();
    }

    public void testProfileImage1(){
        try {
            cpi.ProfileImage image = new cpi.ProfileImage(1.0f,0.5f,0.3f,0.5f,ProfileLabels1);

            java.io.File out = new java.io.File("testProfileImage1.png");

            ProfileImage.Write(image,out);

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

            ProfileImage.Write(image,out);

            assertTrue(true);
        }
        catch (java.io.IOException exc){

            exc.printStackTrace();

            assertTrue(false);
        }
    }
    public void testProfileImage3(){
        try {
            cpi.ProfileImage image = new cpi.ProfileImage(1.0f,0.5f,0.3f,0.5f,ProfileLabels3,ProfileFontLat15);

            java.io.File out = new java.io.File("testProfileImage.png");

            ProfileImage.Write(image,out);

            assertTrue(true);
        }
        catch (java.io.IOException exc){

            exc.printStackTrace();

            assertTrue(false);
        }
    }


    private static void Write(cpi.ProfileImage image, java.io.File out)
        throws java.io.IOException
    {
        byte[] png = image.toPNG();
        java.io.OutputStream os = new java.io.FileOutputStream(out);
        try {
            os.write(png,0,png.length);
            os.flush();
        }
        finally {
            os.close();
        }
    }
}

