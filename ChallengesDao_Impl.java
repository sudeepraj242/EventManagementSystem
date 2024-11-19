package kolmachikhin.alexander.epictodolist.database.challenges;

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
public final class ChallengesDao_Impl implements ChallengesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ChallengeModel> __insertionAdapterOfChallengeModel;

  private final EntityDeletionOrUpdateAdapter<ChallengeModel> __deletionAdapterOfChallengeModel;

  public ChallengesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChallengeModel = new EntityInsertionAdapter<ChallengeModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `challenges` (`title`,`tasks`,`iconId`,`isActive`,`currentDay`,`needDays`,`countFails`,`countCompletes`,`id`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ChallengeModel value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        final String _tmp = ChallengeTaskListConverter.INSTANCE.toJson(value.getTasks());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        stmt.bindLong(3, value.getIconId());
        final int _tmp_1 = value.isActive() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        stmt.bindLong(5, value.getCurrentDay());
        stmt.bindLong(6, value.getNeedDays());
        stmt.bindLong(7, value.getCountFails());
        stmt.bindLong(8, value.getCountCompletes());
        stmt.bindLong(9, value.getId());
      }
    };
    this.__deletionAdapterOfChallengeModel = new EntityDeletionOrUpdateAdapter<ChallengeModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `challenges` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ChallengeModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void save(final ChallengeModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChallengeModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void saveList(final List<ChallengeModel> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChallengeModel.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ChallengeModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfChallengeModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<ChallengeModel>> getLiveList() {
    final String _sql = "SELECT * FROM challenges";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"challenges"}, false, new Callable<List<ChallengeModel>>() {
      @Override
      public List<ChallengeModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "tasks");
          final int _cursorIndexOfIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "iconId");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCurrentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDay");
          final int _cursorIndexOfNeedDays = CursorUtil.getColumnIndexOrThrow(_cursor, "needDays");
          final int _cursorIndexOfCountFails = CursorUtil.getColumnIndexOrThrow(_cursor, "countFails");
          final int _cursorIndexOfCountCompletes = CursorUtil.getColumnIndexOrThrow(_cursor, "countCompletes");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<ChallengeModel> _result = new ArrayList<ChallengeModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ChallengeModel _item;
            _item = new ChallengeModel();
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.setTitle(_tmpTitle);
            final ArrayList<ChallengeTaskModel> _tmpTasks;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTasks)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTasks);
            }
            _tmpTasks = ChallengeTaskListConverter.INSTANCE.fromJson(_tmp);
            _item.setTasks(_tmpTasks);
            final int _tmpIconId;
            _tmpIconId = _cursor.getInt(_cursorIndexOfIconId);
            _item.setIconId(_tmpIconId);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item.setActive(_tmpIsActive);
            final int _tmpCurrentDay;
            _tmpCurrentDay = _cursor.getInt(_cursorIndexOfCurrentDay);
            _item.setCurrentDay(_tmpCurrentDay);
            final int _tmpNeedDays;
            _tmpNeedDays = _cursor.getInt(_cursorIndexOfNeedDays);
            _item.setNeedDays(_tmpNeedDays);
            final int _tmpCountFails;
            _tmpCountFails = _cursor.getInt(_cursorIndexOfCountFails);
            _item.setCountFails(_tmpCountFails);
            final int _tmpCountCompletes;
            _tmpCountCompletes = _cursor.getInt(_cursorIndexOfCountCompletes);
            _item.setCountCompletes(_tmpCountCompletes);
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
