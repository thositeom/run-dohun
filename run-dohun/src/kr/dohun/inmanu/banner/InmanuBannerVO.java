package kr.dohun.inmanu.banner;

public class InmanuBannerVO {
	/** Page */
	private int startRow = 1;
	private int endRow = 10;
	private int currentPage=1;
	private int rows;
	
	/** InmanuBannerVO */
	private	int bannerIdx;
	private	String bannerName;
	private	String bannerUrl;
	private	String bannerDesc;
	private	String bannerType;
	private	String createDate;
	private	String updateDAte;
	
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getBannerIdx() {
		return bannerIdx;
	}
	public void setBannerIdx(int bannerIdx) {
		this.bannerIdx = bannerIdx;
	}
	public String getBannerName() {
		return bannerName;
	}
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public String getBannerDesc() {
		return bannerDesc;
	}
	public void setBannerDesc(String bannerDesc) {
		this.bannerDesc = bannerDesc;
	}
	public String getBannerType() {
		return bannerType;
	}
	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDAte() {
		return updateDAte;
	}
	public void setUpdateDAte(String updateDAte) {
		this.updateDAte = updateDAte;
	}
}
