package com.codingexercise.adobe.core.models;

public class DamAssetDetails {

	private String assetName;
	private String mimeType;

	private String title;
	private String description;
	private String path;

	public DamAssetDetails(String assetName, String mimeType, String title, String description, String path) {
		this.assetName = assetName;
		this.mimeType = mimeType;
		this.title = title;
		this.description = description;
		this.path = path;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
