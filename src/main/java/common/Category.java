/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author Andrei
 */
public class Category {

    private int id;
    private String name;

    public Category(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Category(int id) {
        super();
        this.id = id;
        this.name = "";
    }

    public Category() {
        this.id = 0;
        this.name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
