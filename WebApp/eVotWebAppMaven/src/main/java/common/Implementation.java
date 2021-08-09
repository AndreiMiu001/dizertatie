/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import dao.DAO;

/**
 *
 * @author Andrei
 */
public abstract class Implementation {

    protected final DAO mDao;

    public Implementation() {
        mDao = new DAO();
    }
}
