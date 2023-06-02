package coop.constellation.connectorservices.ssosample.service;

import javax.inject.Singleton;
import java.util.Map;
import java.util.HashMap;
import com.xtensifi.connectorservices.common.accesslayer.HttpRequest;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

@Singleton
public class BasicTemplateImp implements IBasicTemplateHandler { 

    /* Generic logger for the class */
    private static Logger logger = LoggerFactory.getLogger(BasicTemplateImp.class); 

    /* Init() - Initialize BasicTemplateImp */
    public void init() {
        logger.info("BasicTemplateImp.Init() - Ran");
        
    }

    /* requestExternalData() - Simple Method to grab data from external(to Consetellation) backend end. */
    public String requestExternalData(String url){
        logger.info("BasicTemplateImp.Method1() - Ran");
        String extResponse = null;
        
        // CREATE HTTP REQUEST - HELPER IS HERE TO MAKE EXTERNAL CALLS 
        HttpRequest dspHttpRequest = new HttpRequest();

        try {
            
            // HTTP REQUEST - BUILD & SET HEADER - For External call
            Map<String, String> headers = new HashMap<String, String>(); 
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            dspHttpRequest.setHeaders(headers);

            // HTTP REQUEST - SET IS HTTP
            dspHttpRequest.setIsHttp(true);
            
            // HTTP REQUEST - SET URL
            dspHttpRequest.setUrl(url);

            // HTTP REQUEST - SET METHOD - GET OR POST
            dspHttpRequest.setMethod("GET");

            // HTTP REQUEST - SET URL Parameters 
            // String urlParameters = "";
            // if (ParamValue != "" && ParamValue != null){
            //     urlParameters = ParamName.toLowerCase() + "=" + URLEncoder.encode(ParamValue, "UTF-8"); 
            //     dspHttpRequest.setUrlParameters(urlParameters);
            // }
            // EXECUTE HTTP REQUEST - NOTE: Response is in the format needed {\"response\": {RESPONSE IS HERE}}"
            extResponse = dspHttpRequest.executeRequest();

        } catch (Exception ex) {
            logger.error("BasicTemplateImp.requestExternalDate - Exception:" + ex.getMessage());
            extResponse = "{\"data\": {}, \"success\": false}";
        }
        // NOTE External Response format is: 
        return extResponse;
    }
}

