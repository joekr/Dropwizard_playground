package com.temperature;

import com.google.inject.AbstractModule;
import com.temperature.db.RoomDao;

public class RoomModule extends AbstractModule {
  @Override
  protected void configure() {
    // Beter example would be a more abstract DAO, but whatever
    // bind(RoomDao.class).to();
  }
}
