package common;

import dao.DAO;

public abstract class Implementation {
    protected final DAO mDao;

    public Implementation() {
        mDao = new DAO();
    }
}
