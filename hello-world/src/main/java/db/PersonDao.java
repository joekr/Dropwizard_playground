package com.helloworld.db;


import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import com.helloworld.db.mapper.PersonMapper;
import com.helloworld.core.Person;

public interface PersonDao {

  @SqlQuery("select * from person where id = :id")
  @RegisterRowMapper(PersonMapper.class)
  public Person getPerson(@Bind("id") final long id);
}
