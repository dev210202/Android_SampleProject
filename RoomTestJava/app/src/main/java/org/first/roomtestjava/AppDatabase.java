package org.first.roomtestjava;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Todo.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {
   public abstract TodoDao todoDao();
}
