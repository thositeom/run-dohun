package org.springframework.orm.ibatis.support;

import com.ibatis.sqlmap.engine.type.BaseTypeHandler;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public abstract class AbstractLobTypeHandler extends BaseTypeHandler {
	public static final int LOB_CREATOR_SYNCHRONIZATION_ORDER = 800;
	private LobHandler lobHandler;

	public AbstractLobTypeHandler() {
		this(SqlMapClientFactoryBean.getConfigTimeLobHandler());
	}

	protected AbstractLobTypeHandler(LobHandler lobHandler) {
		if (lobHandler == null) {
			throw new IllegalStateException(
					"No LobHandler found for configuration - lobHandler property must be set on SqlMapClientFactoryBean");
		}

		this.lobHandler = lobHandler;
	}

	public final void setParameter(PreparedStatement ps, int i, Object parameter, String jdbcType) throws SQLException {
		if (!TransactionSynchronizationManager.isSynchronizationActive()) {
			throw new IllegalStateException(
					"Spring transaction synchronization needs to be active for setting values in iBATIS TypeHandlers that delegate to a Spring LobHandler");
		}

		LobCreator lobCreator = this.lobHandler.getLobCreator();
		try {
			setParameterInternal(ps, i, parameter, jdbcType, lobCreator);
		} catch (IOException ex) {
			throw new SQLException("I/O errors during LOB access: " + ex.getMessage());
		}

		TransactionSynchronizationManager.registerSynchronization(new LobCreatorSynchronization(lobCreator));
	}

	public final Object getResult(ResultSet rs, String columnName) throws SQLException {
		return getResult(rs, rs.findColumn(columnName));
	}

	public final Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		try {
			return getResultInternal(rs, columnIndex, this.lobHandler);
		} catch (IOException ex) {
			throw new SQLException("I/O errors during LOB access: " + ex.getClass().getName() + ": " + ex.getMessage());
		}
	}

	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		throw new SQLException("Retrieving LOBs from a CallableStatement is not supported");
	}

	protected abstract void setParameterInternal(PreparedStatement paramPreparedStatement, int paramInt,
			Object paramObject, String paramString, LobCreator paramLobCreator) throws SQLException, IOException;

	protected abstract Object getResultInternal(ResultSet paramResultSet, int paramInt, LobHandler paramLobHandler)
			throws SQLException, IOException;

	private static class LobCreatorSynchronization extends TransactionSynchronizationAdapter {
		private final LobCreator lobCreator;

		public LobCreatorSynchronization(LobCreator lobCreator) {
			this.lobCreator = lobCreator;
		}

		public int getOrder() {
			return 800;
		}

		public void beforeCompletion() {
			this.lobCreator.close();
		}
	}
}
