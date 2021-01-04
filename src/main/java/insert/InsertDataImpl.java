/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insert;

import common.Category;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Andrei
 */
public class InsertDataImpl {

    private ArrayList<Category> mCategoryArray;

    public InsertDataImpl () {
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

}
