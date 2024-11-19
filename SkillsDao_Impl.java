package kolmachikhin.alexander.epictodolist.database.skills;

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
public final class SkillsDao_Impl implements SkillsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SkillModel> __insertionAdapterOfSkillModel;

  private final EntityDeletionOrUpdateAdapter<SkillModel> __deletionAdapterOfSkillModel;

  public SkillsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSkillModel = new EntityInsertionAdapter<SkillModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `skills` (`title`,`attribute`,`iconId`,`progress`,`id`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SkillModel value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        stmt.bindLong(2, value.getAttribute());
        stmt.bindLong(3, value.getIconId());
        stmt.bindLong(4, value.getProgress());
        stmt.bindLong(5, value.getId());
      }
    };
    this.__deletionAdapterOfSkillModel = new EntityDeletionOrUpdateAdapter<SkillModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `skills` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SkillModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void save(final SkillModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSkillModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void saveList(final List<SkillModel> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSkillModel.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final SkillModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSkillModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<SkillModel>> getLiveList() {
    final String _sql = "SELECT * FROM skills";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"skills"}, false, new Callable<List<SkillModel>>() {
      @Override
      public List<SkillModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAttribute = CursorUtil.getColumnIndexOrThrow(_cursor, "attribute");
          final int _cursorIndexOfIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "iconId");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<SkillModel> _result = new ArrayList<SkillModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SkillModel _item;
            _item = new SkillModel();
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.setTitle(_tmpTitle);
            final int _tmpAttribute;
            _tmpAttribute = _cursor.getInt(_cursorIndexOfAttribute);
            _item.setAttribute(_tmpAttribute);
            final int _tmpIconId;
            _tmpIconId = _cursor.getInt(_cursorIndexOfIconId);
            _item.setIconId(_tmpIconId);
            final int _tmpProgress;
            _tmpProgress = _cursor.getInt(_cursorIndexOfProgress);
            _item.setProgress(_tmpProgress);
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
