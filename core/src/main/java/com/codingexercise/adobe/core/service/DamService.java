package com.codingexercise.adobe.core.service;

import org.apache.sling.api.resource.ResourceResolver;

public interface DamService {

	public String getAllChildAssets(ResourceResolver resourceResolver, long start);
}