package com.codingexercise.adobe.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.codingexercise.adobe.core.service.DamService;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=DAM Service Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/apps/myproject/getImages", })
public class GetDamImageServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = -5377655161332622973L;

	@Reference
	private DamService damService;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

		String offsetStr = request.getParameter("offset").trim();
		long offset = (StringUtils.isNotBlank(offsetStr)) ? Long.parseLong(offsetStr) : 0;

		response.getWriter().write(damService.getAllChildAssets(request.getResourceResolver(), offset));

	}
}
