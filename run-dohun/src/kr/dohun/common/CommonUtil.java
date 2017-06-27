package kr.dohun.common;

public class CommonUtil {

	
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
	
	/**
	 * @param qty
	 * @param orderList
	 * @return 수량*단가=금액
	 * dohun 2017. 6. 27.
	 * 
	 */
	public static String getQtyToAmount(String qty, String orderList){
		int payment = 0;
		
		switch (orderList) {
		case "잡화":
			payment = Integer.parseInt(qty) * 40000;
			break;
		case "아카시아":
			payment = Integer.parseInt(qty) * 40000;
			break;
		case "꽃화분":
			payment = Integer.parseInt(qty) * 60000;
			break;
		case "프로폴리스":
			payment = Integer.parseInt(qty) * 100000;
			break;
		default:
			break;
		}
		return Integer.toString(payment); 
	}
	
	public static String getQtyToCost(String orderList){
		String cost = "";
		
		switch (orderList) {
		case "잡화":
			cost = "40000";
			break;
		case "아카시아":
			cost = "40000";
			break;
		case "꽃화분":
			cost = "60000";
			break;
		case "프로폴리스":
			cost = "100000";
			break;
		default:
			break;
		}
		
		return cost; 
	}
}
