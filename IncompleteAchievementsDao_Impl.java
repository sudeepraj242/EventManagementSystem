package kolmachikhin.alexander.epictodolist.database.achievements;

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
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class IncompleteAchievementsDao_Impl implements IncompleteAchievementsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<IncompleteAchievementModel> __insertionAdapterOfIncompleteAchievementModel;

  private final EntityDeletionOrUpdateAdapter<IncompleteAchievementModel> __deletionAdapterOfIncompleteAchievementModel;

  public IncompleteAchievementsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIncompleteAchievementModel = new EntityInsertionAdapter<IncompleteAchievementModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `incomplete_achievements` (`isAchieved`,`id`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IncompleteAchievementModel value) {
        final int _tmp = value.isAchieved() ? 1 : 0;
        stmt.bindLong(1, _tmp);
        stmt.bindLong(2, value.getId());
      }
    };
    this.__deletionAdapterOfIncompleteAchievementModel = new EntityDeletionOrUpdateAdapter<IncompleteAchievementModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `incomplete_achievements` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IncompleteAchievementModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void save(final IncompleteAchievementModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfIncompleteAchievementModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void saveList(final List<? extends IncompleteAchievementModel> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfIncompleteAchievementModel.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final IncompleteAchievementModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfIncompleteAchievementModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public IncompleteAchievementModel getById(final int id) {
    final String _sql = "SELECT * FROM incomplete_achievements WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIsAchieved = CursorUtil.getColumnIndexOrThrow(_cursor, "isAchieved");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final IncompleteAchievementModel _result;
      if(_cursor.moveToFirst()) {
        _result = new IncompleteAchievementModel();
        final boolean _tmpIsAchieved;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsAchieved);
        _tmpIsAchieved = _tmp != 0;
        _result.setAchieved(_tmpIsAchieved);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<IncompleteAchievementModel>> getLiveList() {
    final String _sql = "SELECT * FROM incomplete_achievements";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"incomplete_achievements"}, false, new Callable<List<IncompleteAchievementModel>>() {
      @Override
      public List<IncompleteAchievementModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIsAchieved = CursorUtil.getColumnIndexOrThrow(_cursor, "isAchieved");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<IncompleteAchievementModel> _result = new ArrayList<IncompleteAchievementModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final IncompleteAchievementModel _item;
            _item = new IncompleteAchievementModel();
            final boolean _tmpIsAchieved;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAchieved);
            _tmpIsAchieved = _tmp != 0;
            _item.setAchieved(_tmpIsAchieved);
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

  @Override
  public List<IncompleteAchievementModel> getList() {
    final String _sql = "SELECT * FROM incomplete_achievements";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIsAchieved = CursorUtil.getColumnIndexOrThrow(_cursor, "isAchieved");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<IncompleteAchievementModel> _result = new ArrayList<IncompleteAchievementModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final IncompleteAchievementModel _item;
        _item = new IncompleteAchievementModel();
        final boolean _tmpIsAchieved;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsAchieved);
        _tmpIsAchieved = _tmp != 0;
        _item.setAchieved(_tmpIsAchieved);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
