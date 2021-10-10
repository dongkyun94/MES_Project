package mainboard.model;

import java.util.List;

public class PageInfo {

	private int page;
	private int totalPage;
	private int startPage;
	private int endPage;
	private int listCount;
	private List<MainBoard> content;
	
	public PageInfo(List<MainBoard> content, int page, int limit, int total) {
		this.page = page;
		this.totalPage = (int)((double) total / limit + 0.95);
		this.startPage = (((int) ((double)page / 10 + 0.9))-1) * 10 + 1;
		this.endPage = startPage + 10 - 1;
		if(endPage > totalPage) {this.endPage = totalPage;}
		this.listCount = total;
		this.content = content;
	}

	public boolean hasBoards() {
		return listCount > 0;
	}

	public boolean hasNoBoards() {
		return listCount == 0;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getListCount() {
		return listCount;
	}

	public List<MainBoard> getContent() {
		return content;
	}

}
