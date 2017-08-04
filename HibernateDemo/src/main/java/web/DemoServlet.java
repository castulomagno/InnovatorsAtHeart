package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.edmx.EdmxReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.StorageTorneo;
import service.DemoEdmProvider;
import service.DemoEntityCollectionProcessor;
import service.DemoEntityProcessor;
import service.DemoPrimitiveProcessor;

public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DemoServlet.class);

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	    	
	        HttpSession session = req.getSession(true);
	        StorageTorneo storageTorneo = (StorageTorneo) session.getAttribute(StorageTorneo.class.getName());
	        if (storageTorneo == null) {
	           storageTorneo = new StorageTorneo();
	           session.setAttribute(StorageTorneo.class.getName(), storageTorneo);
	        }
	      
	        // create odata handler and configure it with EdmProvider and Processor
	        OData odata = OData.newInstance();
	        ServiceMetadata edm = odata.createServiceMetadata(new DemoEdmProvider(), new ArrayList<EdmxReference>());
	        ODataHttpHandler handler = odata.createHandler(edm);
	        handler.register(new DemoEntityCollectionProcessor(storageTorneo));
	        handler.register(new DemoEntityProcessor(storageTorneo));
	        handler.register(new DemoPrimitiveProcessor(storageTorneo));
	        
	        handler.process(req, resp);
	  } catch (RuntimeException e) {
			LOG.error("Server Error occurred in ExampleServlet", e);
			throw new ServletException(e);
		}
	}
}
