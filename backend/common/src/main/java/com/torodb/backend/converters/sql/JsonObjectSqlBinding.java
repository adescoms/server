/*
 *     This file is part of ToroDB.
 *
 *     ToroDB is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ToroDB is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with ToroDB. If not, see <http://www.gnu.org/licenses/>.
 *
 *     Copyright (c) 2014, 8Kdata Technology
 *     
 */

package com.torodb.backend.converters.sql;

import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;

public class JsonObjectSqlBinding implements SqlBinding<JsonObject> {
    public static final JsonObjectSqlBinding INSTANCE = new JsonObjectSqlBinding();

    @Override
    public JsonObject get(ResultSet resultSet, int columnIndex) throws SQLException {
        String value = resultSet.getString(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        }
        return Json.createReader(new StringReader(value)).readObject();
    }

    @Override
    public void set(PreparedStatement preparedStatement, int parameterIndex, JsonObject value) throws SQLException {
        preparedStatement.setString(parameterIndex, value.toString());
    }
}
