package org.springframework.orm.ibatis;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import java.sql.SQLException;

public abstract interface SqlMapClientCallback<T> {
	public abstract T doInSqlMapClient(SqlMapExecutor paramSqlMapExecutor) throws SQLException;
}
