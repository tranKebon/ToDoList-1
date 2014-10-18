/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrjaffesclass.apcs.todolist.MessageClasses;

/**
 *
 * @author Roger
 */
public class ChangeDescriptionMessage {

  private final int id;
  private final String description;
  
  public ChangeDescriptionMessage(int _id, String _description) {
    id = _id;
    description = _description;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }
  
}
