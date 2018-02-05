package kr.dohun.inmanu.code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("InmanuCodeDAO")
public class InmanuCodeDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<InmanuCodeVO> InmanuCodeTopList(){
		List<InmanuCodeVO> resultList = this.jdbcTemplate.query(
				  "SELECT * FROM CODE_TOP",
				  new RowMapper<InmanuCodeVO>() {
				    public InmanuCodeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				    	InmanuCodeVO vo = new InmanuCodeVO();
				    	vo.setTopCode(rs.getString("CODE"));
				    	vo.setTopCodeName(rs.getString("CODE_NAME"));
				    	vo.setTopCodeDesc(rs.getString("CODE_DESC"));
				      return vo;
				    }
				  });
		return resultList;
	}

	

	public List<InmanuCodeVO> InmanuCodeSubList(String topCode){
		List<InmanuCodeVO> resultList = this.jdbcTemplate.query(
				  "SELECT * FROM CODE_SUB WHERE CODE_TOP = ? ORDER BY CODE_ORDER ASC ",
				  new Object[]{
						  topCode	  
				  },
				  new RowMapper<InmanuCodeVO>() {
				    public InmanuCodeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				    	InmanuCodeVO vo = new InmanuCodeVO();
				    	vo.setSubCode(rs.getString("CODE"));
				    	vo.setSubCodeDesc(rs.getString("CODE_DESC"));
				    	vo.setSubCodeName(rs.getString("CODE_NAME"));
				    	vo.setSubCodeOrder(rs.getString("CODE_ORDER"));
				      return vo;
				    }
				  });
		return resultList;
	}

	public void inmanuTopCodeAdd(InmanuCodeVO vo){
		this.jdbcTemplate.update(
				"INSERT INTO CODE_TOP(CODE, CODE_NAME, CODE_DESC) VALUES (?, ?, ?)",
				new Object[] {
						vo.getTopCode(),
						vo.getTopCodeName(),
						vo.getTopCodeDesc()
				});
	}

	public void inmanuTopCodeDelete(InmanuCodeVO vo){
		this.jdbcTemplate.update(
				"DELETE FROM CODE_TOP WHERE CODE = ? ",
				new Object[] {
						vo.getTopCode(),
				});
	}

	public void inmanuSubCodeDelete(InmanuCodeVO vo){
		this.jdbcTemplate.update(
				"DELETE FROM CODE_SUB WHERE CODE_TOP = ? ",
				new Object[] {
						vo.getTopCode(),
				});
	}
}