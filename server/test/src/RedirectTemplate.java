
import cpi.Redirect;
import oso.data.Person;

/**
 * 
 */
public class RedirectTemplate
    extends AbstractTest
{

    private final static String Href = "http://www.host.com/results?st={{=st}}&sf={{=sf}}&nf={{=nf}}&nt={{=nt}}&id={{=identifier}}";
    private final static String Target = "target";
    private final static String Sequence = "inject";
    private final static String Timeout = "0";

    private final static float St = 0.84782606f;
    private final static float Sf = 1.0f;
    private final static float Nt = 0.6304348f;
    private final static float Nf = 0.5652174f;
    private final static String Identifier = "c252LllNBVpp8cp3";

    private final static String Expected = "http://www.host.com/results?st="+St+"&sf="+Sf+"&nf="+Nf+"&nt="+Nt+"&id="+Identifier;



    public RedirectTemplate(){
        super();
    }

    public void testRedirectTemplate(){
        Person person = new Person(Identifier);
        person.setNt(Nt);
        person.setNf(Nf);
        person.setSf(Sf);
        person.setSt(St);


        Redirect redirect = new Redirect(Href,Target,Sequence,Timeout);
        try {
            String result = redirect.toString(person);

            System.err.println();
            System.err.println("[Result] "+result);
            System.err.println("[Expect] "+Expected);
            System.err.println();

            assertTrue(result.equals(Expected));
        }
        catch (Exception exc){

            exc.printStackTrace();

            assertTrue(false);
        }
    }


}

