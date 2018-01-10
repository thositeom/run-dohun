package org.springframework.orm.ibatis.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

public class BlobSerializableTypeHandler extends AbstractLobTypeHandler {
	public BlobSerializableTypeHandler() {
	}

	protected BlobSerializableTypeHandler(LobHandler lobHandler) {
		super(lobHandler);
	}

	protected void setParameterInternal(PreparedStatement ps, int index, Object value, String jdbcType,
			LobCreator lobCreator) throws SQLException, IOException {
		if (value != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			try {
				oos.writeObject(value);
				oos.flush();
				lobCreator.setBlobAsBytes(ps, index, baos.toByteArray());
			} finally {
				oos.close();
			}
		} else {
			lobCreator.setBlobAsBytes(ps, index, null);
		}
	}

	protected Object getResultInternal(ResultSet rs, int index, LobHandler lobHandler)
			throws SQLException, IOException {
		InputStream is = lobHandler.getBlobAsBinaryStream(rs, index);
		if (is != null) {
			ObjectInputStream ois = new ObjectInputStream(is);
			try {
				return ois.readObject();
			} catch (ClassNotFoundException ex) {
				throw new SQLException("Could not deserialize BLOB contents: " + ex.getMessage());
			} finally {
				ois.close();
			}
		}

		return null;
	}

	public Object valueOf(String s) {
		return s;
	}
}
