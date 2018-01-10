package org.springframework.orm.ibatis.support;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

public class ClobStringTypeHandler extends AbstractLobTypeHandler {
	public ClobStringTypeHandler() {
	}

	protected ClobStringTypeHandler(LobHandler lobHandler) {
		super(lobHandler);
	}

	protected void setParameterInternal(PreparedStatement ps, int index, Object value, String jdbcType,
			LobCreator lobCreator) throws SQLException {
		lobCreator.setClobAsString(ps, index, (String) value);
	}

	protected Object getResultInternal(ResultSet rs, int index, LobHandler lobHandler) throws SQLException {
		return lobHandler.getClobAsString(rs, index);
	}

	public Object valueOf(String s) {
		return s;
	}
}
