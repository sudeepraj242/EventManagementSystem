package kolmachikhin.alexander.epictodolist.database.tasks.completed;

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
public final class CompletedTasksDao_Impl implements CompletedTasksDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CompletedTaskModel> __insertionAdapterOfCompletedTaskModel;

  private final EntityDeletionOrUpdateAdapter<CompletedTaskModel> __deletionAdapterOfCompletedTaskModel;

  public CompletedTasksDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCompletedTaskModel = new EntityInsertionAdapter<CompletedTaskModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `completed_tasks` (`completionDate`,`isDeleted`,`content`,`difficulty`,`skillId`,`id`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CompletedTaskModel value) {
        stmt.bindLong(1, value.getCompletionDate());
        final int _tmp = value.isDeleted() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        if (value.getContent() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getContent());
        }
        stmt.bindLong(4, value.getDifficulty());
        stmt.bindLong(5, value.getSkillId());
        stmt.bindLong(6, value.getId());
      }
    };
    this.__deletionAdapterOfCompletedTaskModel = new EntityDeletionOrUpdateAdapter<CompletedTaskModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `completed_tasks` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CompletedTaskModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void save(final CompletedTaskModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCompletedTaskModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void saveList(final List<CompletedTaskModel> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCompletedTaskModel.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CompletedTaskModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCompletedTaskModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<CompletedTaskModel>> getLiveList() {
    final String _sql = "SELECT * FROM completed_tasks";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"completed_tasks"}, false, new Callable<List<CompletedTaskModel>>() {
      @Override
      public List<CompletedTaskModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCompletionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "completionDate");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfSkillId = CursorUtil.getColumnIndexOrThrow(_cursor, "skillId");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<CompletedTaskModel> _result = new ArrayList<CompletedTaskModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CompletedTaskModel _item;
            _item = new CompletedTaskModel();
            final long _tmpCompletionDate;
            _tmpCompletionDate = _cursor.getLong(_cursorIndexOfCompletionDate);
            _item.setCompletionDate(_tmpCompletionDate);
            final boolean _tmpIsDeleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp != 0;
            _item.setDeleted(_tmpIsDeleted);
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
