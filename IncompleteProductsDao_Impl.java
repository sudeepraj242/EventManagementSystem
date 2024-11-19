package kolmachikhin.alexander.epictodolist.database.products;

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
public final class IncompleteProductsDao_Impl implements IncompleteProductsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<IncompleteProductModel> __insertionAdapterOfIncompleteProductModel;

  private final EntityDeletionOrUpdateAdapter<IncompleteProductModel> __deletionAdapterOfIncompleteProductModel;

  public IncompleteProductsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIncompleteProductModel = new EntityInsertionAdapter<IncompleteProductModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `incomplete_products` (`type`,`countParts`,`id`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IncompleteProductModel value) {
        stmt.bindLong(1, value.getType());
        stmt.bindLong(2, value.getCountParts());
        stmt.bindLong(3, value.getId());
      }
    };
    this.__deletionAdapterOfIncompleteProductModel = new EntityDeletionOrUpdateAdapter<IncompleteProductModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `incomplete_products` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IncompleteProductModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void save(final IncompleteProductModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfIncompleteProductModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void saveList(final List<? extends IncompleteProductModel> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfIncompleteProductModel.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final IncompleteProductModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfIncompleteProductModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<IncompleteProductModel>> getLiveList() {
    final String _sql = "SELECT * FROM incomplete_products";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"incomplete_products"}, false, new Callable<List<IncompleteProductModel>>() {
      @Override
      public List<IncompleteProductModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCountParts = CursorUtil.getColumnIndexOrThrow(_cursor, "countParts");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<IncompleteProductModel> _result = new ArrayList<IncompleteProductModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final IncompleteProductModel _item;
            _item = new IncompleteProductModel();
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _item.setType(_tmpType);
            final int _tmpCountParts;
            _tmpCountParts = _cursor.getInt(_cursorIndexOfCountParts);
            _item.setCountParts(_tmpCountParts);
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
  public IncompleteProductModel getById(final int id) {
    final String _sql = "SELECT * FROM incomplete_products WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfCountParts = CursorUtil.getColumnIndexOrThrow(_cursor, "countParts");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final IncompleteProductModel _result;
      if(_cursor.moveToFirst()) {
        _result = new IncompleteProductModel();
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _result.setType(_tmpType);
        final int _tmpCountParts;
        _tmpCountParts = _cursor.getInt(_cursorIndexOfCountParts);
        _result.setCountParts(_tmpCountParts);
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
