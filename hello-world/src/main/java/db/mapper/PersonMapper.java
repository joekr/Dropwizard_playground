package com.helloworld.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import com.helloworld.core.Person;

public class PersonMapper implements RowMapper<Person> {
  private static final String id = "id";
  private static final String firstName = "first_name";
  private static final String lastName = "last_name";

  public PersonMapper(){

  }

  public Person map(ResultSet resultSet, StatementContext statementContext)
      throws SQLException {
    return new Person(resultSet.getInt(id), resultSet.getString(firstName), resultSet.getString(lastName));
  }
}
