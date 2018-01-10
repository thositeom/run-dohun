package org.springframework.orm.ibatis.support;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

public class BlobByteArrayTypeHandler extends AbstractLobTypeHandler {
	public BlobByteArrayTypeHandler() {
	}

	protected BlobByteArrayTypeHandler(LobHandler lobHandler) {
		super(lobHandler);
	}

	protected void setParameterInternal(PreparedStatement ps, int index, Object value, String jdbcType,
			LobCreator lobCreator) throws SQLException {
		lobCreator.setBlobAsBytes(ps, index, (byte[]) value);
	}

	protected Object getResultInternal(ResultSet rs, int index, LobHandler lobHandler) throws SQLException {
		return lobHandler.getBlobAsBytes(rs, index);
	}

	public Object valueOf(String s) {
		return s.getBytes();
	}
}
