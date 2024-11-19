package kolmachikhin.alexander.epictodolist.database.tasks.repeatable;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kolmachikhin.alexander.epictodolist.database.BoolListConverter;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RepeatableTasksDao_Impl implements RepeatableTasksDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RepeatableTaskModel> __insertionAdapterOfRepeatableTaskModel;

  private final EntityDeletionOrUpdateAdapter<RepeatableTaskModel> __deletionAdapterOfRepeatableTaskModel;

  public RepeatableTasksDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRepeatableTaskModel = new EntityInsertionAdapter<RepeatableTaskModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `repeatable_tasks` (`copyDays`,`copyTime`,`notCreateIfExists`,`content`,`difficulty`,`skillId`,`id`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RepeatableTaskModel value) {
        final String _tmp = BoolListConverter.INSTANCE.toJson(value.getCopyDays());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
        stmt.bindLong(2, value.getCopyTime());
        final int _tmp_1 = value.getNotCreateIfExists() ? 1 : 0;
        stmt.bindLong(3, _tmp_1);
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        stmt.bindLong(5, value.getDifficulty());
        stmt.bindLong(6, value.getSkillId());
        stmt.bindLong(7, value.getId());
      }
    };
    this.__deletionAdapterOfRepeatableTaskModel = new EntityDeletionOrUpdateAdapter<RepeatableTaskModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `repeatable_tasks` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RepeatableTaskModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void save(final RepeatableTaskModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRepeatableTaskModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void saveList(final List<RepeatableTaskModel> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRepeatableTaskModel.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final RepeatableTaskModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfRepeatableTaskModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<RepeatableTaskModel>> getLiveList() {
    final String _sql = "SELECT * FROM repeatable_tasks";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"repeatable_tasks"}, false, new Callable<List<RepeatableTaskModel>>() {
      @Override
      public List<RepeatableTaskModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCopyDays = CursorUtil.getColumnIndexOrThrow(_cursor, "copyDays");
          final int _cursorIndexOfCopyTime = CursorUtil.getColumnIndexOrThrow(_cursor, "copyTime");
          final int _cursorIndexOfNotCreateIfExists = CursorUtil.getColumnIndexOrThrow(_cursor, "notCreateIfExists");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfSkillId = CursorUtil.getColumnIndexOrThrow(_cursor, "skillId");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<RepeatableTaskModel> _result = new ArrayList<RepeatableTaskModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final RepeatableTaskModel _item;
            _item = new RepeatableTaskModel();
            final ArrayList<Boolean> _tmpCopyDays;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCopyDays)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCopyDays);
            }
            _tmpCopyDays = BoolListConverter.INSTANCE.fromList(_tmp);
            _item.setCopyDays(_tmpCopyDays);
            final long _tmpCopyTime;
            _tmpCopyTime = _cursor.getLong(_cursorIndexOfCopyTime);
            _item.setCopyTime(_tmpCopyTime);
            final boolean _tmpNotCreateIfExists;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfNotCreateIfExists);
            _tmpNotCreateIfExists = _tmp_1 != 0;
            _item.setNotCreateIfExists(_tmpNotCreateIfExists);
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
