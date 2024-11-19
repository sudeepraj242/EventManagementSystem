package kolmachikhin.alexander.epictodolist.database.tasks.current;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kolmachikhin.alexander.epictodolist.database.IntListConverter;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CurrentTasksDao_Impl implements CurrentTasksDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CurrentTaskModel> __insertionAdapterOfCurrentTaskModel;

  private final EntityDeletionOrUpdateAdapter<CurrentTaskModel> __deletionAdapterOfCurrentTaskModel;

  public CurrentTasksDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCurrentTaskModel = new EntityInsertionAdapter<CurrentTaskModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `current_tasks` (`notificationIds`,`content`,`difficulty`,`skillId`,`id`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CurrentTaskModel value) {
        final String _tmp = IntListConverter.INSTANCE.toJson(value.getNotificationIds());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
        if (value.getContent() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContent());
        }
        stmt.bindLong(3, value.getDifficulty());
        stmt.bindLong(4, value.getSkillId());
        stmt.bindLong(5, value.getId());
      }
    };
    this.__deletionAdapterOfCurrentTaskModel = new EntityDeletionOrUpdateAdapter<CurrentTaskModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `current_tasks` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CurrentTaskModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void save(final CurrentTaskModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCurrentTaskModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void saveList(final List<CurrentTaskModel> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCurrentTaskModel.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CurrentTaskModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCurrentTaskModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<CurrentTaskModel>> getLiveList() {
    final String _sql = "SELECT * FROM current_tasks";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"current_tasks"}, false, new Callable<List<CurrentTaskModel>>() {
      @Override
      public List<CurrentTaskModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNotificationIds = CursorUtil.getColumnIndexOrThrow(_cursor, "notificationIds");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfSkillId = CursorUtil.getColumnIndexOrThrow(_cursor, "skillId");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<CurrentTaskModel> _result = new ArrayList<CurrentTaskModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CurrentTaskModel _item;
            _item = new CurrentTaskModel();
            final ArrayList<Integer> _tmpNotificationIds;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfNotificationIds)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfNotificationIds);
            }
            _tmpNotificationIds = IntListConverter.INSTANCE.fromJson(_tmp);
            _item.setNotificationIds(_tmpNotificationIds);
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            _item.setContent(_tmpContent);
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            _item.setDifficulty(_tmpDifficulty);
            final int _tmpSkillId;
            _tmpSkillId = _cursor.getInt(_cursorIndexOfSkillId);
            _item.setSkillId(_tmpSkillId);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
