package de.ba.model;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.protocol.ServiceResponse;
import oracle.stellent.ridc.protocol.jaxws.JaxWSClient;
import oracle.stellent.ridc.protocol.jaxws.JaxWSClientConfig;

/**
 * L.Pelov
 * Simple code access the UCM Native Web Service.
 */
public class CallRidc {
    private String ridcName = "Ridc UCM";

    public CallRidc() {
    }

    private void createConnection() throws IdcClientException {

        // create the manager
        IdcClientManager manager = new IdcClientManager();

        // build a client that will communicate using the JAXWS protocol
        JaxWSClient idcClient =
            (JaxWSClient)manager.createClient("http://127.0.0.1:16200/idcnativews");

        // get the config object and set properties
        idcClient.getConfig().setSocketTimeout(30000); // 30 seconds
        idcClient.getConfig().setConnectionSize(20); // 20 connections

        JaxWSClientConfig jaxwsConfig = idcClient.getConfig();

        // set the property
        jaxwsConfig.setServerInstanceName("/cs/");

        // create the user context
        IdcContext idcContext = new IdcContext("weblogic", "welcome1");

        // build the search request binder
        DataBinder binder = idcClient.createBinder();
        binder.putLocal("IdcService", "GET_SEARCH_RESULTS");
        binder.putLocal("QueryText", "");
        binder.putLocal("ResultCount", "20");

        // send the initial request
        ServiceResponse response = idcClient.sendRequest(idcContext, binder);
        DataBinder responseBinder = response.getResponseAsBinder();

        DataResultSet resultSet = responseBinder.getResultSet("SearchResults");

        // loop over the results
        for (DataObject dataObject : resultSet.getRows()) {
            System.out.println("Title is: " + dataObject.get("dDocTitle"));
            System.out.println("Author is: " + dataObject.get("dDocAuthor"));
        }

    }

    public String getRidcName() {
        try {
            createConnection();
        } catch (IdcClientException e) {
            return "Exception: " + e.getMessage();
        }
        return ridcName;
    }
}
