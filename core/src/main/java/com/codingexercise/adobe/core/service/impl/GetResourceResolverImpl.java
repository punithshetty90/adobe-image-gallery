package com.codingexercise.adobe.core.service.impl;

import java.util.HashMap;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codingexercise.adobe.core.service.GetResourceResolver;

import org.apache.sling.api.resource.LoginException;

@Component(service = GetResourceResolver.class)
public class GetResourceResolverImpl implements GetResourceResolver {
	
	@Reference
	ResourceResolverFactory resolverFactory;
	

	private static final Logger log = LoggerFactory.getLogger(GetResourceResolverImpl.class);

	@Override
	public ResourceResolver getServiceResolver() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "getresourceresolver");
		ResourceResolver resolver = null;
		try {
			resolver = resolverFactory.getServiceResourceResolver(param);
		} catch (LoginException e) {

			log.error("Login Exception! Failed to get Service Resource Resolver" + e.getMessage());
		}
		return resolver;

	}
}
