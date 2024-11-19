package kolmachikhin.alexander.epictodolist.database.notifications;

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
public final class NotificationsDao_Impl implements NotificationsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NotificationModel> __insertionAdapterOfNotificationModel;

  private final EntityDeletionOrUpdateAdapter<NotificationModel> __deletionAdapterOfNotificationModel;

  public NotificationsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotificationModel = new EntityInsertionAdapter<NotificationModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `notifications` (`title`,`content`,`time`,`id`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotificationModel value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getContent() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContent());
        }
        stmt.bindLong(3, value.getTime());
        stmt.bindLong(4, value.getId());
      }
    };
    this.__deletionAdapterOfNotificationModel = new EntityDeletionOrUpdateAdapter<NotificationModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `notifications` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotificationModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void save(final NotificationModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNotificationModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void saveList(final List<NotificationModel> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNotificationModel.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final NotificationModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNotificationModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<NotificationModel>> getLiveList() {
    final String _sql = "SELECT * FROM notifications";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"notifications"}, false, new Callable<List<NotificationModel>>() {
      @Override
      public List<NotificationModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<NotificationModel> _result = new ArrayList<NotificationModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final NotificationModel _item;
            _item = new NotificationModel();
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.setTitle(_tmpTitle);
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            _item.setContent(_tmpContent);
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            _item.setTime(_tmpTime);
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
