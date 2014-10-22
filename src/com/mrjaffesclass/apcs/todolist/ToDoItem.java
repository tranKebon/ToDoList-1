/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrjaffesclass.apcs.todolist;

/**
 *
 * @author Roger
 */
public class ToDoItem {

  private int id;  // id can't be changed
  private String description;
  private boolean done;
  
  public ToDoItem(int _id, String _description) {
    description = _description;
    id = _id;
    done = false;     // Default to not completed
  }

  public ToDoItem(int _id, String _description, boolean _done) {
    description = _description;
    id = _id;
    done = _done;     // Default to not completed
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }
  
  public void toggleDone() {
    this.done = !this.done;
  }

  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public void merge(ToDoItem anotherItem) {
    this.setDescription(anotherItem.getDescription());
    this.setDone(anotherItem.isDone());    
  }

}
