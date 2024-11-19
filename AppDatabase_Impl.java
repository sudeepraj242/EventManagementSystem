package kolmachikhin.alexander.epictodolist.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kolmachikhin.alexander.epictodolist.database.achievements.IncompleteAchievementsDao;
import kolmachikhin.alexander.epictodolist.database.achievements.IncompleteAchievementsDao_Impl;
import kolmachikhin.alexander.epictodolist.database.challenges.ChallengesDao;
import kolmachikhin.alexander.epictodolist.database.challenges.ChallengesDao_Impl;
import kolmachikhin.alexander.epictodolist.database.notifications.NotificationsDao;
import kolmachikhin.alexander.epictodolist.database.notifications.NotificationsDao_Impl;
import kolmachikhin.alexander.epictodolist.database.products.IncompleteProductsDao;
import kolmachikhin.alexander.epictodolist.database.products.IncompleteProductsDao_Impl;
import kolmachikhin.alexander.epictodolist.database.skills.SkillsDao;
import kolmachikhin.alexander.epictodolist.database.skills.SkillsDao_Impl;
import kolmachikhin.alexander.epictodolist.database.tasks.completed.CompletedTasksDao;
import kolmachikhin.alexander.epictodolist.database.tasks.completed.CompletedTasksDao_Impl;
import kolmachikhin.alexander.epictodolist.database.tasks.current.CurrentTasksDao;
import kolmachikhin.alexander.epictodolist.database.tasks.current.CurrentTasksDao_Impl;
import kolmachikhin.alexander.epictodolist.database.tasks.repeatable.RepeatableTasksDao;
import kolmachikhin.alexander.epictodolist.database.tasks.repeatable.RepeatableTasksDao_Impl;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile IncompleteAchievementsDao _incompleteAchievementsDao;

  private volatile ChallengesDao _challengesDao;

  private volatile NotificationsDao _notificationsDao;

  private volatile IncompleteProductsDao _incompleteProductsDao;

  private volatile SkillsDao _skillsDao;

  private volatile CompletedTasksDao _completedTasksDao;

  private volatile CurrentTasksDao _currentTasksDao;

  private volatile RepeatableTasksDao _repeatableTasksDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `incomplete_achievements` (`isAchieved` INTEGER NOT NULL, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `challenges` (`title` TEXT, `tasks` TEXT, `iconId` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, `currentDay` INTEGER NOT NULL, `needDays` INTEGER NOT NULL, `countFails` INTEGER NOT NULL, `countCompletes` INTEGER NOT NULL, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `notifications` (`title` TEXT, `content` TEXT, `time` INTEGER NOT NULL, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `incomplete_products` (`type` INTEGER NOT NULL, `countParts` INTEGER NOT NULL, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `skills` (`title` TEXT, `attribute` INTEGER NOT NULL, `iconId` INTEGER NOT NULL, `progress` INTEGER NOT NULL, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `completed_tasks` (`completionDate` INTEGER NOT NULL, `isDeleted` INTEGER NOT NULL, `content` TEXT, `difficulty` INTEGER NOT NULL, `skillId` INTEGER NOT NULL, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `current_tasks` (`notificationIds` TEXT, `content` TEXT, `difficulty` INTEGER NOT NULL, `skillId` INTEGER NOT NULL, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `repeatable_tasks` (`copyDays` TEXT, `copyTime` INTEGER NOT NULL, `notCreateIfExists` INTEGER NOT NULL, `content` TEXT, `difficulty` INTEGER NOT NULL, `skillId` INTEGER NOT NULL, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fb16f0530aa2349ad3744762e8282df8')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `incomplete_achievements`");
        _db.execSQL("DROP TABLE IF EXISTS `challenges`");
        _db.execSQL("DROP TABLE IF EXISTS `notifications`");
        _db.execSQL("DROP TABLE IF EXISTS `incomplete_products`");
        _db.execSQL("DROP TABLE IF EXISTS `skills`");
        _db.execSQL("DROP TABLE IF EXISTS `completed_tasks`");
        _db.execSQL("DROP TABLE IF EXISTS `current_tasks`");
        _db.execSQL("DROP TABLE IF EXISTS `repeatable_tasks`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsIncompleteAchievements = new HashMap<String, TableInfo.Column>(2);
        _columnsIncompleteAchievements.put("isAchieved", new TableInfo.Column("isAchieved", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncompleteAchievements.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIncompleteAchievements = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIncompleteAchievements = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoIncompleteAchievements = new TableInfo("incomplete_achievements", _columnsIncompleteAchievements, _foreignKeysIncompleteAchievements, _indicesIncompleteAchievements);
        final TableInfo _existingIncompleteAchievements = TableInfo.read(_db, "incomplete_achievements");
        if (! _infoIncompleteAchievements.equals(_existingIncompleteAchievements)) {
          return new RoomOpenHelper.ValidationResult(false, "incomplete_achievements(kolmachikhin.alexander.epictodolist.database.achievements.IncompleteAchievementModel).\n"
                  + " Expected:\n" + _infoIncompleteAchievements + "\n"
                  + " Found:\n" + _existingIncompleteAchievements);
        }
        final HashMap<String, TableInfo.Column> _columnsChallenges = new HashMap<String, TableInfo.Column>(9);
        _columnsChallenges.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("tasks", new TableInfo.Column("tasks", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("iconId", new TableInfo.Column("iconId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("currentDay", new TableInfo.Column("currentDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("needDays", new TableInfo.Column("needDays", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("countFails", new TableInfo.Column("countFails", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("countCompletes", new TableInfo.Column("countCompletes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChallenges = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChallenges = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChallenges = new TableInfo("challenges", _columnsChallenges, _foreignKeysChallenges, _indicesChallenges);
        final TableInfo _existingChallenges = TableInfo.read(_db, "challenges");
        if (! _infoChallenges.equals(_existingChallenges)) {
          return new RoomOpenHelper.ValidationResult(false, "challenges(kolmachikhin.alexander.epictodolist.database.challenges.ChallengeModel).\n"
                  + " Expected:\n" + _infoChallenges + "\n"
                  + " Found:\n" + _existingChallenges);
        }
        final HashMap<String, TableInfo.Column> _columnsNotifications = new HashMap<String, TableInfo.Column>(4);
        _columnsNotifications.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotifications.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotifications.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotifications.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotifications = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotifications = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotifications = new TableInfo("notifications", _columnsNotifications, _foreignKeysNotifications, _indicesNotifications);
        final TableInfo _existingNotifications = TableInfo.read(_db, "notifications");
        if (! _infoNotifications.equals(_existingNotifications)) {
          return new RoomOpenHelper.ValidationResult(false, "notifications(kolmachikhin.alexander.epictodolist.database.notifications.NotificationModel).\n"
                  + " Expected:\n" + _infoNotifications + "\n"
                  + " Found:\n" + _existingNotifications);
        }
        final HashMap<String, TableInfo.Column> _columnsIncompleteProducts = new HashMap<String, TableInfo.Column>(3);
        _columnsIncompleteProducts.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncompleteProducts.put("countParts", new TableInfo.Column("countParts", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncompleteProducts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIncompleteProducts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIncompleteProducts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoIncompleteProducts = new TableInfo("incomplete_products", _columnsIncompleteProducts, _foreignKeysIncompleteProducts, _indicesIncompleteProducts);
        final TableInfo _existingIncompleteProducts = TableInfo.read(_db, "incomplete_products");
        if (! _infoIncompleteProducts.equals(_existingIncompleteProducts)) {
          return new RoomOpenHelper.ValidationResult(false, "incomplete_products(kolmachikhin.alexander.epictodolist.database.products.IncompleteProductModel).\n"
                  + " Expected:\n" + _infoIncompleteProducts + "\n"
                  + " Found:\n" + _existingIncompleteProducts);
        }
        final HashMap<String, TableInfo.Column> _columnsSkills = new HashMap<String, TableInfo.Column>(5);
        _columnsSkills.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkills.put("attribute", new TableInfo.Column("attribute", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkills.put("iconId", new TableInfo.Column("iconId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkills.put("progress", new TableInfo.Column("progress", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkills.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSkills = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSkills = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSkills = new TableInfo("skills", _columnsSkills, _foreignKeysSkills, _indicesSkills);
        final TableInfo _existingSkills = TableInfo.read(_db, "skills");
        if (! _infoSkills.equals(_existingSkills)) {
          return new RoomOpenHelper.ValidationResult(false, "skills(kolmachikhin.alexander.epictodolist.database.skills.SkillModel).\n"
                  + " Expected:\n" + _infoSkills + "\n"
                  + " Found:\n" + _existingSkills);
        }
        final HashMap<String, TableInfo.Column> _columnsCompletedTasks = new HashMap<String, TableInfo.Column>(6);
        _columnsCompletedTasks.put("completionDate", new TableInfo.Column("completionDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletedTasks.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletedTasks.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletedTasks.put("difficulty", new TableInfo.Column("difficulty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletedTasks.put("skillId", new TableInfo.Column("skillId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletedTasks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCompletedTasks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCompletedTasks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCompletedTasks = new TableInfo("completed_tasks", _columnsCompletedTasks, _foreignKeysCompletedTasks, _indicesCompletedTasks);
        final TableInfo _existingCompletedTasks = TableInfo.read(_db, "completed_tasks");
        if (! _infoCompletedTasks.equals(_existingCompletedTasks)) {
          return new RoomOpenHelper.ValidationResult(false, "completed_tasks(kolmachikhin.alexander.epictodolist.database.tasks.completed.CompletedTaskModel).\n"
                  + " Expected:\n" + _infoCompletedTasks + "\n"
                  + " Found:\n" + _existingCompletedTasks);
        }
        final HashMap<String, TableInfo.Column> _columnsCurrentTasks = new HashMap<String, TableInfo.Column>(5);
        _columnsCurrentTasks.put("notificationIds", new TableInfo.Column("notificationIds", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrentTasks.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrentTasks.put("difficulty", new TableInfo.Column("difficulty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrentTasks.put("skillId", new TableInfo.Column("skillId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrentTasks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCurrentTasks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCurrentTasks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCurrentTasks = new TableInfo("current_tasks", _columnsCurrentTasks, _foreignKeysCurrentTasks, _indicesCurrentTasks);
        final TableInfo _existingCurrentTasks = TableInfo.read(_db, "current_tasks");
        if (! _infoCurrentTasks.equals(_existingCurrentTasks)) {
          return new RoomOpenHelper.ValidationResult(false, "current_tasks(kolmachikhin.alexander.epictodolist.database.tasks.current.CurrentTaskModel).\n"
                  + " Expected:\n" + _infoCurrentTasks + "\n"
                  + " Found:\n" + _existingCurrentTasks);
        }
        final HashMap<String, TableInfo.Column> _columnsRepeatableTasks = new HashMap<String, TableInfo.Column>(7);
        _columnsRepeatableTasks.put("copyDays", new TableInfo.Column("copyDays", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepeatableTasks.put("copyTime", new TableInfo.Column("copyTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepeatableTasks.put("notCreateIfExists", new TableInfo.Column("notCreateIfExists", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepeatableTasks.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepeatableTasks.put("difficulty", new TableInfo.Column("difficulty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepeatableTasks.put("skillId", new TableInfo.Column("skillId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepeatableTasks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRepeatableTasks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRepeatableTasks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRepeatableTasks = new TableInfo("repeatable_tasks", _columnsRepeatableTasks, _foreignKeysRepeatableTasks, _indicesRepeatableTasks);
        final TableInfo _existingRepeatableTasks = TableInfo.read(_db, "repeatable_tasks");
        if (! _infoRepeatableTasks.equals(_existingRepeatableTasks)) {
          return new RoomOpenHelper.ValidationResult(false, "repeatable_tasks(kolmachikhin.alexander.epictodolist.database.tasks.repeatable.RepeatableTaskModel).\n"
                  + " Expected:\n" + _infoRepeatableTasks + "\n"
                  + " Found:\n" + _existingRepeatableTasks);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fb16f0530aa2349ad3744762e8282df8", "b5629639343d63f3a1210f8ed13d173e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "incomplete_achievements","challenges","notifications","incomplete_products","skills","completed_tasks","current_tasks","repeatable_tasks");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `incomplete_achievements`");
      _db.execSQL("DELETE FROM `challenges`");
      _db.execSQL("DELETE FROM `notifications`");
      _db.execSQL("DELETE FROM `incomplete_products`");
      _db.execSQL("DELETE FROM `skills`");
      _db.execSQL("DELETE FROM `completed_tasks`");
      _db.execSQL("DELETE FROM `current_tasks`");
      _db.execSQL("DELETE FROM `repeatable_tasks`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(IncompleteAchievementsDao.class, IncompleteAchievementsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ChallengesDao.class, ChallengesDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NotificationsDao.class, NotificationsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(IncompleteProductsDao.class, IncompleteProductsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SkillsDao.class, SkillsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CompletedTasksDao.class, CompletedTasksDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CurrentTasksDao.class, CurrentTasksDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RepeatableTasksDao.class, RepeatableTasksDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public IncompleteAchievementsDao incompleteAchievementsDao() {
    if (_incompleteAchievementsDao != null) {
      return _incompleteAchievementsDao;
    } else {
      synchronized(this) {
        if(_incompleteAchievementsDao == null) {
          _incompleteAchievementsDao = new IncompleteAchievementsDao_Impl(this);
        }
        return _incompleteAchievementsDao;
      }
    }
  }

  @Override
  public ChallengesDao challengesDao() {
    if (_challengesDao != null) {
      return _challengesDao;
    } else {
      synchronized(this) {
        if(_challengesDao == null) {
          _challengesDao = new ChallengesDao_Impl(this);
        }
        return _challengesDao;
      }
    }
  }

  @Override
  public NotificationsDao notificationsDao() {
    if (_notificationsDao != null) {
      return _notificationsDao;
    } else {
      synchronized(this) {
        if(_notificationsDao == null) {
          _notificationsDao = new NotificationsDao_Impl(this);
        }
        return _notificationsDao;
      }
    }
  }

  @Override
  public IncompleteProductsDao incompleteProductsDao() {
    if (_incompleteProductsDao != null) {
      return _incompleteProductsDao;
    } else {
      synchronized(this) {
        if(_incompleteProductsDao == null) {
          _incompleteProductsDao = new IncompleteProductsDao_Impl(this);
        }
        return _incompleteProductsDao;
      }
    }
  }

  @Override
  public SkillsDao skillsDao() {
    if (_skillsDao != null) {
      return _skillsDao;
    } else {
      synchronized(this) {
        if(_skillsDao == null) {
          _skillsDao = new SkillsDao_Impl(this);
        }
        return _skillsDao;
      }
    }
  }

  @Override
  public CompletedTasksDao completedTasksDao() {
    if (_completedTasksDao != null) {
      return _completedTasksDao;
    } else {
      synchronized(this) {
        if(_completedTasksDao == null) {
          _completedTasksDao = new CompletedTasksDao_Impl(this);
        }
        return _completedTasksDao;
      }
    }
  }

  @Override
  public CurrentTasksDao currentTasksDao() {
    if (_currentTasksDao != null) {
      return _currentTasksDao;
    } else {
      synchronized(this) {
        if(_currentTasksDao == null) {
          _currentTasksDao = new CurrentTasksDao_Impl(this);
        }
        return _currentTasksDao;
      }
    }
  }

  @Override
  public RepeatableTasksDao repeatableTasksDao() {
    if (_repeatableTasksDao != null) {
      return _repeatableTasksDao;
    } else {
      synchronized(this) {
        if(_repeatableTasksDao == null) {
          _repeatableTasksDao = new RepeatableTasksDao_Impl(this);
        }
        return _repeatableTasksDao;
      }
    }
  }
}
