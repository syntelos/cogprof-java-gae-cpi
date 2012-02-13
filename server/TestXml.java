 
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
 
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class TestXml {

    public static String ToString(Document document)
        throws IOException
    {
        StringWriter writer = new StringWriter();

        new XMLSerializer(writer, null).serialize(document);

        return writer.toString();
    }
    public static Document Read(File file)
        throws SAXException, IOException, ParserConfigurationException
    {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }
    public static Document Read(String xml)
        throws SAXException, IOException, ParserConfigurationException
    {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
    }

    public static void main(String[] argv){
        try {
            File file = new File(argv[0]);

            Document doc = Read(file);

            System.out.println(ToString(doc));

            System.exit(0);
        }
        catch (Exception exc){
            exc.printStackTrace();
            System.exit(1);
        }
    }
}
