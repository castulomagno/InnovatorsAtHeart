package data;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;

import impl.TorneoImpl;
import model.Torneo;
import service.DemoEdmProvider;
import util.Util;

public class StorageTorneo {

	private List<Entity> torneosList;

	public StorageTorneo() {
		super();
		torneosList = new ArrayList<Entity>();
		initSampleData();
	}

	/* PUBLIC FACADE */

	public EntityCollection readEntitySetData(EdmEntitySet edmEntitySet) throws ODataApplicationException {

		// actually, this is only required if we have more than one Entity Sets
		if (edmEntitySet.getName().equals(DemoEdmProvider.ES_TORNEOS_NAME)) {
			System.out.println(" -- readEntitySetData: "+edmEntitySet.getName());
			return getTorneos();
		}
		return null;
	}

	public Entity readEntityData(EdmEntitySet edmEntitySet, List<UriParameter> keyParams)
			throws ODataApplicationException {

		EdmEntityType edmEntityType = edmEntitySet.getEntityType();

		// actually, this is only required if we have more than one Entity Type
		if (edmEntityType.getName().equals(DemoEdmProvider.ET_TORNEO_NAME)) {
			return getTorneo(edmEntityType, keyParams);
		}
		return null;
	}

	/* INTERNAL */

	private EntityCollection getTorneos() {
		EntityCollection retEntitySet = new EntityCollection();

		for (Entity torneoEntity : this.torneosList) {
			retEntitySet.getEntities().add(torneoEntity);
		}
		
		return retEntitySet;
	}

	private Entity getTorneo(EdmEntityType edmEntityType, List<UriParameter> keyParams)
			throws ODataApplicationException {

		// the list of entities at runtime
		EntityCollection entitySet = getTorneos();

		/* generic approach to find the requested entity */
		Entity requestedEntity = Util.findEntity(edmEntityType, entitySet, keyParams);

		if (requestedEntity == null) {
			throw new ODataApplicationException("Entity for requested key doesn't exist",
					HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
		}

		return requestedEntity;
	}
	
	//private EntityCollection getData(EdmEntitySet edmEntitySet) {
	private void initSampleData(){

		TorneoImpl ti = new TorneoImpl();
		List<Torneo> tl = ti.getTorneos();
		Entity e1;
		
		for(Torneo t : tl)
		{				
			e1 = new Entity().addProperty(new Property(null, "IdTorneo", ValueType.PRIMITIVE, t.getIdTorneo()))
					.addProperty(new Property(null, "Nombre", ValueType.PRIMITIVE, t.getNombre()));
			
			e1.setId(createId("Torneos", t.getIdTorneo()));
			torneosList.add(e1);
			
		
		}

	}

	private URI createId(String entitySetName, Object id) {
		try {
			return new URI(entitySetName + "(" + String.valueOf(id) + ")");
		} catch (URISyntaxException e) {
			throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
		}
	}
}
