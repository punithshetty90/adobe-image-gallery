package com.codingexercise.adobe.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;



@ObjectClassDefinition(
		name = "DAM Service Config", 
		description = "This configuration reads the DAM path and return the assets under it in JSON format")
public @interface DamServiceConfig {

	
	@AttributeDefinition(
			name = "DAM Path", 
			description = "Enter the dam path under which the assets are pulled")
	public String getDamPath() default "/content/dam/we-retail/en";
	
	@AttributeDefinition(
			name = "Hits Per Page", 
			description = "Enter the limit which the query can fetch")
	public long getHitsPerPage()  default 20;

}
