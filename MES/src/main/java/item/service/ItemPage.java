package item.service;

import java.util.List;

import item.model.Item;


public class ItemPage {
	private int total;
	private int currentPage;
	private List<Item> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public ItemPage(int total, int cuurentPage, int size, List<Item> content) {
		this.total = total;
		this.currentPage = cuurentPage;
		this.content =  content;
		if(total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1 ;
			if(modVal == 0) startPage -=5;
			
			endPage = startPage + 4 ;
			if(endPage> totalPages) endPage = totalPages;
		}
	}
	public int getTotal() {
		return total;
	}
	public boolean hasNoItems() {
		return total==0;
	}
	public boolean hasItems() {
		return total>0;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Item> getContent() {
		return content;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	

}
