package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;

public class DemoEdmProvider extends CsdlAbstractEdmProvider {

	// Service Namespace
	public static final String NAMESPACE = "OData.Demo";

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

	// Entity Types Names
	public static final String ET_TORNEO_NAME = "Torneo";
	public static final FullQualifiedName ET_TORNEO_FQN = new FullQualifiedName(NAMESPACE, ET_TORNEO_NAME);

	// Entity Set Names
	public static final String ES_TORNEOS_NAME = "Torneos";

	public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {

		// this method is called for one of the EntityTypes that are configured
		// in the Schema
		if (entityTypeName.equals(ET_TORNEO_FQN)) {

			// create EntityType properties
			CsdlProperty idTorneo = new CsdlProperty().setName("IdTorneo")
					.setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
			CsdlProperty nombre = new CsdlProperty().setName("Nombre")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

			// create CsdlPropertyRef for Key element
			CsdlPropertyRef propertyRef = new CsdlPropertyRef();
			propertyRef.setName("IdTorneo");

			// configure EntityType
			CsdlEntityType entityType = new CsdlEntityType();
			entityType.setName(ET_TORNEO_NAME);
			entityType.setProperties(Arrays.asList(idTorneo, nombre));
			entityType.setKey(Collections.singletonList(propertyRef));

			return entityType;
		}
		return null;
	}

	public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {

		if (entityContainer.equals(CONTAINER)) {
			if (entitySetName.equals(ES_TORNEOS_NAME)) {
				CsdlEntitySet entitySet = new CsdlEntitySet();
				entitySet.setName(ES_TORNEOS_NAME);
				entitySet.setType(ET_TORNEO_FQN);

				return entitySet;
			}
		}
		return null;
	}

	public CsdlEntityContainer getEntityContainer() {

		// create EntitySets
		List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
		entitySets.add(getEntitySet(CONTAINER, ES_TORNEOS_NAME));

		// create EntityContainer
		CsdlEntityContainer entityContainer = new CsdlEntityContainer();
		entityContainer.setName(CONTAINER_NAME);
		entityContainer.setEntitySets(entitySets);

		return entityContainer;
	}

	public List<CsdlSchema> getSchemas() {

		// create Schema
		CsdlSchema schema = new CsdlSchema();
		schema.setNamespace(NAMESPACE);

		// add EntityTypes
		List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
		entityTypes.add(getEntityType(ET_TORNEO_FQN));
		schema.setEntityTypes(entityTypes);

		// add EntityContainer
		schema.setEntityContainer(getEntityContainer());

		// finally
		List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
		schemas.add(schema);

		return schemas;
	}
	
	public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {

	    // This method is invoked when displaying the Service Document at e.g. http://localhost:8080/DemoService/DemoService.svc
	    if (entityContainerName == null || entityContainerName.equals(CONTAINER)) {
	        CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
	        entityContainerInfo.setContainerName(CONTAINER);
	        return entityContainerInfo;
	    }

	    return null;
	}
}
