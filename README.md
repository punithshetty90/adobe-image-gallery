# Adobe - AEM Image Gallery


## Assumptions:

1) Did not create a new system user, Since we will be just reading the DAM path, I will use the following systemuser "dam-reader-service" to get the resource resolver. Mapping can be found in /apps/myproject/osgiconfig/config/org.apache.sling.serviceusermapping.impl.ServiceUserMapperImpl.xml


## Modules

The main parts of the projects are:

* core: Contains the OSGI config, DAM service and a servlet to get all images in JSON
* ui.apps: Custom template that inherits from weretail/components/structure/page. Override the body.html and added code to display the image gallery
* ui.content: Contains editable templates and its policies
* ui.config: contains runmode specific OSGi configs for DAM Service.
* all: a single content package that embeds all of the compiled modules 

## How to build

To build all the modules and deploy the `all` package to a local instance of AEM, run in the project root directory the following command:

	cd <PROJECT-ROOT-DIRECTORY>
    mvn clean install -PautoInstallSinglePackage



## JAVA files

1) DamServiceConfig.java : Contains the OSGI java config, Can configure hits per page (for pagination) and DAM Path
2) DamAssetDetails.java & DamAssetsModel.java : POJO Java model for storing the asset details and query details
3) DamService.java & DamServiceImpl.java : Service to get all the images under the path configured in OSGI in JSON format
5) GetDamImageServlet.java : Serve the JSON using GET request. Accepts start query parameter. Sample: /apps/myproject/getImages?start=0

## OSGI Config

1) http://<server>:<port>/system/console/configMgr  "DAM Service Config" -> 
Default values are
DAM Path: /content/dam/we-retail/en
Hits Per Page: 20 (This config is for pagination)

## Page Template:

1) Name of the template: "Adobe Coding Page Template"

## Steps to create a new page using "Adobe Coding Page Template"
1) Navigate to /content/we-retail -> Page Properties -> http://<server>:<port>/mnt/overlay/wcm/core/content/sites/properties.html?item=%2Fcontent%2Fwe-retail
2) Navigate to "Advanced" tab -> Add a new entry under "Allowed Template" -> /conf/myproject/settings/wcm/templates/.*
3) http://<server>:<port>/sites.html/content/we-retail/language-masters/en -> Create new Page using "Adobe Coding Page Template"

## Screenshot

![Alt text](https://github.com/punithshetty123/myproject/blob/main/screenshots/Screen%20Shot%202021-10-24%20at%208.16.31%20PM.png?raw=true "Image Gallery")

![Alt text](https://github.com/punithshetty123/myproject/blob/main/screenshots/Screen%20Shot%202021-10-24%20at%208.17.24%20PM.png?raw=true "OSGI Config")
