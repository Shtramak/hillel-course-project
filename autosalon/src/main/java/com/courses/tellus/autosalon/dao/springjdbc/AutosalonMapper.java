package com.courses.tellus.autosalon.dao.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.courses.tellus.autosalon.model.Autosalon;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class AutosalonMapper implements RowMapper<Autosalon> {
    /**
     *Implementation RowMapper for Autosalon.
     *
     * @param resultSet to save.
     * @param rowNum to save.
     * @return autosalon.
     * @throws SQLException exception.
     */
    @Nullable
    @Override
    public Autosalon mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        final Autosalon autosalon = new Autosalon();
        autosalon.setId(resultSet.getLong("id"));
        autosalon.setName(resultSet.getString("name"));
        autosalon.setAddress(resultSet.getString("address"));
        autosalon.setTelephone(resultSet.getString("telephone"));
        return autosalon;
    }
}
