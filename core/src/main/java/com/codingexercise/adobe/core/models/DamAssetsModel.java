package com.codingexercise.adobe.core.models;

import java.util.List;

public class DamAssetsModel {

	private long hitsPerPage;
	private long totalMatches;
	private long offset;
	private long numberOfPages;
	private List<DamAssetDetails> assets;
	
	
	public DamAssetsModel(long hitsPerPage, long totalMatches, long offset, long numberOfPages, List<DamAssetDetails> assets) {
		this.hitsPerPage = hitsPerPage;
		this.totalMatches = totalMatches;
		this.offset = offset;
		this.numberOfPages = numberOfPages;
		this.assets = assets;
	}
	

	public List<DamAssetDetails> getAssets() {
		return assets;
	}

	public void setAssets(List<DamAssetDetails> assets) {
		this.assets = assets;
	}

	public long getHitsPerPage() {
		return hitsPerPage;
	}

	public void setHitsPerPage(long hitsPerPage) {
		this.hitsPerPage = hitsPerPage;
	}

	public long getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(long totalMatches) {
		this.totalMatches = totalMatches;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public long getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(long numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
	
	
	

}
