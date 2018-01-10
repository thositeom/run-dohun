package org.springframework.orm.ibatis.support;

import com.ibatis.sqlmap.client.SqlMapClient;
import javax.sql.DataSource;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.util.Assert;

public abstract class SqlMapClientDaoSupport extends DaoSupport {
	private SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate();

	private boolean externalTemplate = false;

	public final void setDataSource(DataSource dataSource) {
		if (!this.externalTemplate)
			this.sqlMapClientTemplate.setDataSource(dataSource);
	}

	public final DataSource getDataSource() {
		return this.sqlMapClientTemplate.getDataSource();
	}

	public final void setSqlMapClient(SqlMapClient sqlMapClient) {
		if (!this.externalTemplate)
			this.sqlMapClientTemplate.setSqlMapClient(sqlMapClient);
	}

	public final SqlMapClient getSqlMapClient() {
		return this.sqlMapClientTemplate.getSqlMapClient();
	}

	public final void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		Assert.notNull(sqlMapClientTemplate, "SqlMapClientTemplate must not be null");
		this.sqlMapClientTemplate = sqlMapClientTemplate;
		this.externalTemplate = true;
	}

	public final SqlMapClientTemplate getSqlMapClientTemplate() {
		return this.sqlMapClientTemplate;
	}

	protected final void checkDaoConfig() {
		if (!this.externalTemplate)
			this.sqlMapClientTemplate.afterPropertiesSet();
	}
}
