package com.sport.games.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;

public class SportGamesServiceFactory extends ODataJPAServiceFactory {

	private static final String PERSISTENCE_UNIT_NAME = "SportGames_OData";

	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
		ODataJPAContext oDatJPAContext = this.getODataJPAContext();
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			oDatJPAContext.setEntityManagerFactory(emf);
			oDatJPAContext.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
			return oDatJPAContext;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}