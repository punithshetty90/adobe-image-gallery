package com.codingexercise.adobe.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codingexercise.adobe.core.configs.DamServiceConfig;
import com.codingexercise.adobe.core.models.DamAssetDetails;
import com.codingexercise.adobe.core.models.DamAssetsModel;
import com.codingexercise.adobe.core.service.DamService;
import com.codingexercise.adobe.core.service.GetResourceResolver;
import com.day.cq.dam.api.Asset;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.google.common.net.UrlEscapers;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

@Component(service = DamService.class, immediate = true)
@Designate(ocd = DamServiceConfig.class)
public class DamServiceImpl implements DamService {

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(DamServiceImpl.class);

	/**
	 * Instance of the OSGi configuration class
	 */
	private DamServiceConfig configuration;

	/**
	 * Injecting the QueryBuilder dependency
	 */
	@Reference
	private QueryBuilder builder;

	/**
	 * Session object
	 */
	private Session session;

	@Reference
	private GetResourceResolver getResourceResolver;

	@Activate
	protected void activate(DamServiceConfig configuration) {
		this.configuration = configuration;
	}

	@Override
	public String getAllChildAssets(long start) {
		String damPath = configuration.getDamPath();
		if (StringUtils.isNotBlank(damPath)) {
			try {

				/**
				 * Adapting the resource resolver to the session object
				 */
				session = getResourceResolver.getServiceResolver().adaptTo(Session.class);
				/**
				 * Map for the predicates
				 */
				Map<String, String> predicate = new HashMap<>();

				/**
				 * Configuring the Map for the predicate
				 */
				predicate.put("path", damPath);
				predicate.put("type", "dam:Asset");
				predicate.put("1_property", "jcr:content/metadata/dc:format");
				predicate.put("1_property.value", "image/%");
				predicate.put("1_property.operation", "like");
				predicate.put("p.limit", "-1");

				/**
				 * Creating the Query instance
				 */
				Query query = builder.createQuery(PredicateGroup.create(predicate), session);

				query.setStart(start);
				query.setHitsPerPage(configuration.getHitsPerPage());

				/**
				 * Getting the search results
				 */
				SearchResult searchResult = query.getResult();
				
				// paging metadata
			    int hitsPerPage = searchResult.getHits().size();
			    long totalMatches = searchResult.getTotalMatches();
			    long offset = searchResult.getStartIndex();
			    long numberOfPages = totalMatches / configuration.getHitsPerPage();

				
				List<DamAssetDetails> assets = new ArrayList<DamAssetDetails>();
				for (Hit hit : searchResult.getHits()) {

				
					Node hitNode = hit.getNode();
					Node metaDataNode = hit.getNode().getNode("jcr:content/metadata");
					

					
					DamAssetDetails details = new DamAssetDetails(hit.getNode().getName(),
							metaDataNode.getProperty("dc:format").getString(), hit.getTitle(),
							(metaDataNode.hasProperty("dc:description")
									? metaDataNode.getProperty("dc:description").getString()
									: ""),
							UrlEscapers.urlFragmentEscaper().escape(hitNode.getPath()));
					assets.add(details);

				}
				
				DamAssetsModel assetsModel = new DamAssetsModel(hitsPerPage, totalMatches, offset, numberOfPages, assets);
				
				Gson gson = new Gson();
				return gson.toJson(assetsModel);

			} catch (Exception e) {

				log.error(e.getMessage(), e);
			} finally {

				if (session != null) {

					session.logout();
				}

			}
		}
		return null;
	}

}
