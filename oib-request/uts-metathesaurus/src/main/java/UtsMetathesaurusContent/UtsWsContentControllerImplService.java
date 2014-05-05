/**
 * ...
 * <p>
 * -----------------------------------------------------------------------------------<br>
 * (c) 2010-2014 OpenInfobutton Project, Biomedical Informatics, University of Utah<br>
 * Contact: {@code <andrew.iskander@utah.edu>}<br>
 * Biomedical Informatics<br>
 * 421 Wakara Way, Ste 140
 * Salt Lake City, UT 84108-3514<br>
 * Day Phone: 1-801-581-4080<br>
 * -----------------------------------------------------------------------------------
 *
 * @author Andrew Iskander {@code <andrew.iskander@utah.edu>}
 * @version May 5, 2014
 */

package UtsMetathesaurusContent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "UtsWsContentControllerImplService", targetNamespace = "http://webservice.uts.umls.nlm.nih.gov/", wsdlLocation = "https://uts-ws.nlm.nih.gov/services/nwsContent?wsdl")
public class UtsWsContentControllerImplService
    extends Service
{

    private final static URL UTSWSCONTENTCONTROLLERIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(UtsMetathesaurusContent.UtsWsContentControllerImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = UtsMetathesaurusContent.UtsWsContentControllerImplService.class.getResource(".");
            url = new URL(baseUrl, "https://uts-ws.nlm.nih.gov/services/nwsContent?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'https://uts-ws.nlm.nih.gov/services/nwsContent?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        UTSWSCONTENTCONTROLLERIMPLSERVICE_WSDL_LOCATION = url;
    }

    public UtsWsContentControllerImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UtsWsContentControllerImplService() {
        super(UTSWSCONTENTCONTROLLERIMPLSERVICE_WSDL_LOCATION, new QName("http://webservice.uts.umls.nlm.nih.gov/", "UtsWsContentControllerImplService"));
    }

    /**
     * 
     * @return
     *     returns UtsWsContentController
     */
    @WebEndpoint(name = "UtsWsContentControllerImplPort")
    public UtsWsContentController getUtsWsContentControllerImplPort() {
        return super.getPort(new QName("http://webservice.uts.umls.nlm.nih.gov/", "UtsWsContentControllerImplPort"), UtsWsContentController.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UtsWsContentController
     */
    @WebEndpoint(name = "UtsWsContentControllerImplPort")
    public UtsWsContentController getUtsWsContentControllerImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.uts.umls.nlm.nih.gov/", "UtsWsContentControllerImplPort"), UtsWsContentController.class, features);
    }

}
