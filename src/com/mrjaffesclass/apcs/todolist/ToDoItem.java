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

  private final int id;  // id can't be changed
  private String description;
  private boolean done;
  
  public ToDoItem(int _id, String _description) {
    description = _description;
    id = _id;
    done = false;     // Default to not completed
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isCompleted() {
    return done;
  }

  public void completed() {
    this.done = true;
  }
  
  public void notCompleted() {
    this.done = false;
  }

  public int getId() {
    return id;
  }

}
