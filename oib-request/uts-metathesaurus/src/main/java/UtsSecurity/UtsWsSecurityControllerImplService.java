
package UtsSecurity;

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
@WebServiceClient(name = "UtsWsSecurityControllerImplService", targetNamespace = "http://webservice.uts.umls.nlm.nih.gov/", wsdlLocation = "https://uts-ws.nlm.nih.gov/services/nwsSecurity?wsdl")
public class UtsWsSecurityControllerImplService
    extends Service
{

    private final static URL UTSWSSECURITYCONTROLLERIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(UtsSecurity.UtsWsSecurityControllerImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = UtsSecurity.UtsWsSecurityControllerImplService.class.getResource(".");
            url = new URL(baseUrl, "https://uts-ws.nlm.nih.gov/services/nwsSecurity?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'https://uts-ws.nlm.nih.gov/services/nwsSecurity?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        UTSWSSECURITYCONTROLLERIMPLSERVICE_WSDL_LOCATION = url;
    }

    public UtsWsSecurityControllerImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UtsWsSecurityControllerImplService() {
        super(UTSWSSECURITYCONTROLLERIMPLSERVICE_WSDL_LOCATION, new QName("http://webservice.uts.umls.nlm.nih.gov/", "UtsWsSecurityControllerImplService"));
    }

    /**
     * 
     * @return
     *     returns UtsWsSecurityController
     */
    @WebEndpoint(name = "UtsWsSecurityControllerImplPort")
    public UtsWsSecurityController getUtsWsSecurityControllerImplPort() {
        return super.getPort(new QName("http://webservice.uts.umls.nlm.nih.gov/", "UtsWsSecurityControllerImplPort"), UtsWsSecurityController.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UtsWsSecurityController
     */
    @WebEndpoint(name = "UtsWsSecurityControllerImplPort")
    public UtsWsSecurityController getUtsWsSecurityControllerImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.uts.umls.nlm.nih.gov/", "UtsWsSecurityControllerImplPort"), UtsWsSecurityController.class, features);
    }

}
