package org.chzz.demo.common.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;


public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserEntityDao mDao = null;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserEntityDao();
    }

    public final UserEntityDao getDao() {
        return mDao;
    }
}
