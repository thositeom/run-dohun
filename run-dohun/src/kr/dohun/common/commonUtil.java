package kr.dohun.common;

public class commonUtil {

	
	public commonUtil(){
		
	}
	
	/**
	 * @param totalCnt 총건수
	 * @param row 보여줄 갯수
	 * @return
	 */
	public static int getTotalPage(int totalCnt, int row){
		int totalPage = 0; //총 페이지 수 
		totalPage = (int) Math.ceil((double)totalCnt / (double)row);
		return totalPage;
	}
}
