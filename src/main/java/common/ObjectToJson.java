/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
 
public class ObjectToJson {   

    public static String convertElectionsArray(ArrayList<ElectionBean> election) {     
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonInString = gson.toJson(election);
    return jsonInString;
    }
}
