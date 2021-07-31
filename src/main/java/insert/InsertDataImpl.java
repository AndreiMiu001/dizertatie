/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insert;

import common.Category;
import common.Implementation;
import common.Pair;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class InsertDataImpl extends Implementation {

    private ArrayList<Category> mCategoryArray;

    public InsertDataImpl() {
        mCategoryArray = new ArrayList<>();
    }

    public ArrayList<Category> makeElectionCategory() {
        List<String> names = Arrays.asList("Locala", "Judeteana", "Nationala");
        for (int i = 0; i < names.size(); i++) {
            Category category = new Category(i, names.get(i));
            mCategoryArray.add(category);
        }
        return mCategoryArray;
    }

    public ArrayList<Category> getElectionCategories() {
        boolean status = false;
        ArrayList<Category> categories = new ArrayList<>();
        try {
            mDao.connect();
            categories = mDao.getElectionCategories();
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertCandidatesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
}
