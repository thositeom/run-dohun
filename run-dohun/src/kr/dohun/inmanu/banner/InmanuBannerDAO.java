package kr.dohun.inmanu.banner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("InmanuBannerDAO")
public class InmanuBannerDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/** 배너등록 */
	public void inmanuBannerInsert(InmanuBannerVO vo){
		this.jdbcTemplate.update(
				"INSERT INTO BANNER(BANNER_IDX, BANNER_NAME, BANNER_URL, BANNER_DESC, BANNER_TYPE, CREATEDATE, UPDATEDATE ) "
				+ "VALUES (?, ?, ?, ?, ?, NOW(), NOW())",
				new Object[] {
						vo.getBannerIdx()
						,vo.getBannerName()
						,vo.getBannerUrl()
						,vo.getBannerDesc()
						,vo.getBannerType()
				});
	} 
	
	/** 배너삭제 */
	public void inmanuBannerDelete(InmanuBannerVO vo){
		this.jdbcTemplate.update(
				"DELETE FROM BANNER WHERE BANNER_IDX = ? ",
				new Object[] {
						vo.getBannerIdx(),
				});
	}
	
	/** 배너목록 */
	public List<InmanuBannerVO> inmanuBannerList(){
		List<InmanuBannerVO> resultList = this.jdbcTemplate.query(
				  "SELECT * FROM BANNER ORDER BY BANNER_IDX DESC",
				  new RowMapper<InmanuBannerVO>() {
				    public InmanuBannerVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				    	InmanuBannerVO vo = new InmanuBannerVO();
				    	vo.setBannerIdx(Integer.parseInt(rs.getString("BANNER_IDX")));
				    	vo.setBannerName(rs.getString("BANNER_NAME"));
				    	vo.setBannerUrl(rs.getString("BANNER_URL"));
				    	vo.setBannerDesc(rs.getString("BANNER_DESC"));
				    	vo.setBannerType(rs.getString("BANNER_TYPE"));
				    	vo.setCreateDate(rs.getString("CREATEDATE"));
				      return vo;
				    }
				  });
		return resultList;
	}
	
	/** 배너목록Cnt */
	public int inmanuBannerListCnt(){
		//왜죠? 왜 queryForInt가없죠?몰라
		return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BANNER", Integer.class);
	}
	
}
