package com.geroybranda.hero.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HeroDao_Impl implements HeroDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HeroEntity> __insertionAdapterOfHeroEntity;

  private final EntityDeletionOrUpdateAdapter<HeroEntity> __updateAdapterOfHeroEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public HeroDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHeroEntity = new EntityInsertionAdapter<HeroEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `heroes` (`id`,`name`,`appearance`,`personality`,`mission`,`brandRole`,`cartoonIdea`,`sphereLabel`,`emoji`,`createdAt`,`isFavorite`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HeroEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getAppearance());
        statement.bindString(4, entity.getPersonality());
        statement.bindString(5, entity.getMission());
        statement.bindString(6, entity.getBrandRole());
        statement.bindString(7, entity.getCartoonIdea());
        statement.bindString(8, entity.getSphereLabel());
        statement.bindString(9, entity.getEmoji());
        statement.bindLong(10, entity.getCreatedAt());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(11, _tmp);
      }
    };
    this.__updateAdapterOfHeroEntity = new EntityDeletionOrUpdateAdapter<HeroEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `heroes` SET `id` = ?,`name` = ?,`appearance` = ?,`personality` = ?,`mission` = ?,`brandRole` = ?,`cartoonIdea` = ?,`sphereLabel` = ?,`emoji` = ?,`createdAt` = ?,`isFavorite` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HeroEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getAppearance());
        statement.bindString(4, entity.getPersonality());
        statement.bindString(5, entity.getMission());
        statement.bindString(6, entity.getBrandRole());
        statement.bindString(7, entity.getCartoonIdea());
        statement.bindString(8, entity.getSphereLabel());
        statement.bindString(9, entity.getEmoji());
        statement.bindLong(10, entity.getCreatedAt());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindString(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM heroes WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM heroes";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final HeroEntity hero, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHeroEntity.insert(hero);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final HeroEntity hero, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfHeroEntity.handle(hero);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HeroEntity>> observeAll() {
    final String _sql = "SELECT * FROM heroes ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"heroes"}, new Callable<List<HeroEntity>>() {
      @Override
      @NonNull
      public List<HeroEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAppearance = CursorUtil.getColumnIndexOrThrow(_cursor, "appearance");
          final int _cursorIndexOfPersonality = CursorUtil.getColumnIndexOrThrow(_cursor, "personality");
          final int _cursorIndexOfMission = CursorUtil.getColumnIndexOrThrow(_cursor, "mission");
          final int _cursorIndexOfBrandRole = CursorUtil.getColumnIndexOrThrow(_cursor, "brandRole");
          final int _cursorIndexOfCartoonIdea = CursorUtil.getColumnIndexOrThrow(_cursor, "cartoonIdea");
          final int _cursorIndexOfSphereLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "sphereLabel");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<HeroEntity> _result = new ArrayList<HeroEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HeroEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAppearance;
            _tmpAppearance = _cursor.getString(_cursorIndexOfAppearance);
            final String _tmpPersonality;
            _tmpPersonality = _cursor.getString(_cursorIndexOfPersonality);
            final String _tmpMission;
            _tmpMission = _cursor.getString(_cursorIndexOfMission);
            final String _tmpBrandRole;
            _tmpBrandRole = _cursor.getString(_cursorIndexOfBrandRole);
            final String _tmpCartoonIdea;
            _tmpCartoonIdea = _cursor.getString(_cursorIndexOfCartoonIdea);
            final String _tmpSphereLabel;
            _tmpSphereLabel = _cursor.getString(_cursorIndexOfSphereLabel);
            final String _tmpEmoji;
            _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new HeroEntity(_tmpId,_tmpName,_tmpAppearance,_tmpPersonality,_tmpMission,_tmpBrandRole,_tmpCartoonIdea,_tmpSphereLabel,_tmpEmoji,_tmpCreatedAt,_tmpIsFavorite);
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
  public Flow<List<HeroEntity>> observeFavorites() {
    final String _sql = "SELECT * FROM heroes WHERE isFavorite = 1 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"heroes"}, new Callable<List<HeroEntity>>() {
      @Override
      @NonNull
      public List<HeroEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAppearance = CursorUtil.getColumnIndexOrThrow(_cursor, "appearance");
          final int _cursorIndexOfPersonality = CursorUtil.getColumnIndexOrThrow(_cursor, "personality");
          final int _cursorIndexOfMission = CursorUtil.getColumnIndexOrThrow(_cursor, "mission");
          final int _cursorIndexOfBrandRole = CursorUtil.getColumnIndexOrThrow(_cursor, "brandRole");
          final int _cursorIndexOfCartoonIdea = CursorUtil.getColumnIndexOrThrow(_cursor, "cartoonIdea");
          final int _cursorIndexOfSphereLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "sphereLabel");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<HeroEntity> _result = new ArrayList<HeroEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HeroEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAppearance;
            _tmpAppearance = _cursor.getString(_cursorIndexOfAppearance);
            final String _tmpPersonality;
            _tmpPersonality = _cursor.getString(_cursorIndexOfPersonality);
            final String _tmpMission;
            _tmpMission = _cursor.getString(_cursorIndexOfMission);
            final String _tmpBrandRole;
            _tmpBrandRole = _cursor.getString(_cursorIndexOfBrandRole);
            final String _tmpCartoonIdea;
            _tmpCartoonIdea = _cursor.getString(_cursorIndexOfCartoonIdea);
            final String _tmpSphereLabel;
            _tmpSphereLabel = _cursor.getString(_cursorIndexOfSphereLabel);
            final String _tmpEmoji;
            _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _item = new HeroEntity(_tmpId,_tmpName,_tmpAppearance,_tmpPersonality,_tmpMission,_tmpBrandRole,_tmpCartoonIdea,_tmpSphereLabel,_tmpEmoji,_tmpCreatedAt,_tmpIsFavorite);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
