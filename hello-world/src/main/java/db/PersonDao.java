package com.helloworld.db;


import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.helloworld.db.mapper.PersonMapper;
import com.helloworld.core.Person;

public interface PersonDao {

  @SqlQuery("select * from person where id = :id")
  @RegisterRowMapper(PersonMapper.class)
  public Person getPerson(@Bind("id") final long id);

  @SqlUpdate("insert into person (first_name, last_name) values (:firstName, :lastName)")
  void create(@Bind("firstName") String firstName, @Bind("lastName") String lastName);

  @SqlUpdate("delete from person where id = :id")
  void delete(@Bind("id") long id);

  @SqlUpdate("update person set first_name = :firstName, last_name = :lastName where id = :id")
  void update(@Bind("id") long id, @Bind("firstName") String firstName, @Bind("lastName") String lastName);
}
