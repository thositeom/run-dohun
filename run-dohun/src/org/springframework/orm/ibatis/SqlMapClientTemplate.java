package org.springframework.orm.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.ibatis.sqlmap.client.event.RowHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.util.Assert;

public class SqlMapClientTemplate extends JdbcAccessor implements SqlMapClientOperations {
	private SqlMapClient sqlMapClient;

	public SqlMapClientTemplate() {
	}

	public SqlMapClientTemplate(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
		afterPropertiesSet();
	}

	public SqlMapClientTemplate(DataSource dataSource, SqlMapClient sqlMapClient) {
		setDataSource(dataSource);
		setSqlMapClient(sqlMapClient);
		afterPropertiesSet();
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public SqlMapClient getSqlMapClient() {
		return this.sqlMapClient;
	}

	public DataSource getDataSource() {
		DataSource ds = super.getDataSource();
		return ds != null ? ds : this.sqlMapClient.getDataSource();
	}

	public void afterPropertiesSet() {
		if (this.sqlMapClient == null) {
			throw new IllegalArgumentException("Property 'sqlMapClient' is required");
		}
		super.afterPropertiesSet();
	}

	public <T> T execute(SqlMapClientCallback<T> action) throws DataAccessException {
		Assert.notNull(action, "Callback object must not be null");
		Assert.notNull(this.sqlMapClient, "No SqlMapClient specified");

		SqlMapSession session = this.sqlMapClient.openSession();
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Opened SqlMapSession [" + session + "] for iBATIS operation");
		}
		Connection ibatisCon = null;
		try {
			Connection springCon = null;
			DataSource dataSource = getDataSource();
			boolean transactionAware = dataSource instanceof TransactionAwareDataSourceProxy;
			try {
				ibatisCon = session.getCurrentConnection();
				if (ibatisCon == null) {
					springCon = transactionAware ? dataSource.getConnection()
							: DataSourceUtils.doGetConnection(dataSource);
					session.setUserConnection(springCon);
					if (this.logger.isDebugEnabled()) {
						this.logger.debug("Obtained JDBC Connection [" + springCon + "] for iBATIS operation");
					}

				} else if (this.logger.isDebugEnabled()) {
					this.logger.debug("Reusing JDBC Connection [" + ibatisCon + "] for iBATIS operation");
				}
			} catch (SQLException ex) {
				throw new CannotGetJdbcConnectionException("Could not get JDBC Connection", ex);
			}

			try {
				Object localObject2 = action.doInSqlMapClient(session);
				try {
					if (springCon != null) {
						if (transactionAware) {
							springCon.close();
						} else
							DataSourceUtils.doReleaseConnection(springCon, dataSource);
					}
				} catch (Throwable ex) {
					this.logger.debug("Could not close JDBC Connection", ex);
				}
				return (T) localObject2;
			} catch (SQLException ex) {
				throw getExceptionTranslator().translate("SqlMapClient operation", null, ex);
			} finally {
				try {
					if (springCon != null) {
						if (transactionAware) {
							springCon.close();
						} else
							DataSourceUtils.doReleaseConnection(springCon, dataSource);
					}
				} catch (Throwable ex) {
					this.logger.debug("Could not close JDBC Connection", ex);
				}

			}

		} finally {
			if (ibatisCon == null)
				session.close();
		}
	}

	@Deprecated
	public List executeWithListResult(SqlMapClientCallback<List> action) throws DataAccessException {
		return (List) execute(action);
	}

	@Deprecated
	public Map executeWithMapResult(SqlMapClientCallback<Map> action) throws DataAccessException {
		return (Map) execute(action);
	}

	public Object queryForObject(String statementName) throws DataAccessException {
		return queryForObject(statementName, null);
	}

	public Object queryForObject(final String statementName, final Object parameterObject) throws DataAccessException {
		return execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				return executor.queryForObject(statementName, parameterObject);
			}
		});
	}

	public Object queryForObject(final String statementName, final Object parameterObject, final Object resultObject)
			throws DataAccessException {
		return execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				return executor.queryForObject(statementName, parameterObject, resultObject);
			}
		});
	}

	public List queryForList(String statementName) throws DataAccessException {
		return queryForList(statementName, null);
	}

	public List queryForList(final String statementName, final Object parameterObject) throws DataAccessException {
		return (List) execute(new SqlMapClientCallback() {
			public List doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				return executor.queryForList(statementName, parameterObject);
			}
		});
	}

	public List queryForList(String statementName, int skipResults, int maxResults) throws DataAccessException {
		return queryForList(statementName, null, skipResults, maxResults);
	}

	public List queryForList(final String statementName, final Object parameterObject, final int skipResults,
			final int maxResults) throws DataAccessException {
		return (List) execute(new SqlMapClientCallback() {
			public List doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				return executor.queryForList(statementName, parameterObject, skipResults, maxResults);
			}
		});
	}

	public void queryWithRowHandler(String statementName, RowHandler rowHandler) throws DataAccessException {
		queryWithRowHandler(statementName, null, rowHandler);
	}

	public void queryWithRowHandler(final String statementName, final Object parameterObject,
			final RowHandler rowHandler) throws DataAccessException {
		execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.queryWithRowHandler(statementName, parameterObject, rowHandler);
				return null;
			}
		});
	}

	public Map queryForMap(final String statementName, final Object parameterObject, final String keyProperty)
			throws DataAccessException {
		return (Map) execute(new SqlMapClientCallback() {
			public Map doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				return executor.queryForMap(statementName, parameterObject, keyProperty);
			}
		});
	}

	public Map queryForMap(final String statementName, final Object parameterObject, final String keyProperty,
			final String valueProperty) throws DataAccessException {
		return (Map) execute(new SqlMapClientCallback() {
			public Map doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				return executor.queryForMap(statementName, parameterObject, keyProperty, valueProperty);
			}
		});
	}

	public Object insert(String statementName) throws DataAccessException {
		return insert(statementName, null);
	}

	public Object insert(final String statementName, final Object parameterObject) throws DataAccessException {
		return execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				return executor.insert(statementName, parameterObject);
			}
		});
	}

	public int update(String statementName) throws DataAccessException {
		return update(statementName, null);
	}

	public int update(final String statementName, final Object parameterObject) throws DataAccessException {
		return ((Integer) execute(new SqlMapClientCallback() {
			public Integer doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				return Integer.valueOf(executor.update(statementName, parameterObject));
			}
		})).intValue();
	}

	public void update(String statementName, Object parameterObject, int requiredRowsAffected)
			throws DataAccessException {
		int actualRowsAffected = update(statementName, parameterObject);
		if (actualRowsAffected != requiredRowsAffected)
			throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(statementName, requiredRowsAffected,
					actualRowsAffected);
	}

	public int delete(String statementName) throws DataAccessException {
		return delete(statementName, null);
	}

	public int delete(final String statementName, final Object parameterObject) throws DataAccessException {
		return ((Integer) execute(new SqlMapClientCallback() {
			public Integer doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				return Integer.valueOf(executor.delete(statementName, parameterObject));
			}
		})).intValue();
	}

	public void delete(String statementName, Object parameterObject, int requiredRowsAffected)
			throws DataAccessException {
		int actualRowsAffected = delete(statementName, parameterObject);
		if (actualRowsAffected != requiredRowsAffected)
			throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(statementName, requiredRowsAffected,
					actualRowsAffected);
	}
}
