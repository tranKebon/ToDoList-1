package com.mrjaffesclass.apcs.todolist;

import java.util.*;
import com.mrjaffesclass.apcs.messages.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageMailbox {

  // Instance variables
  private final Messaging mvcMessaging;
  private ArrayList<ToDoItem> toDoList;   // This contains our to do list items
  private int nextId = 0;                 // This will tell us what the id of 
                                          // the next item entered should be
  
  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messaging messages) {
    mvcMessaging = messages;
    toDoList = new ArrayList<>();
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    switch (messageName) {
      case "controller:getItems":
        mvcMessaging.notify("model:items", this.getItems());
        break;
      case "controller:getItem":
        mvcMessaging.notify("model:item", this.getItem((int)messagePayload));
        break;
      case "controller:setItemCompleted":
        this.setComplete((int)messagePayload);
        mvcMessaging.notify("model:itemCompleted", messagePayload);
        break;
      case "controller:setItemDescription":
        ChangeDescriptionMessage msg = (ChangeDescriptionMessage)messagePayload;
        this.changeDescription(msg.getId(), msg.getDescription());
        mvcMessaging.notify("model:itemCompleted", messagePayload);
        break;
    }
    // Print debug messages to the console. Remove these when our app is working
    if (messagePayload != null) {
      System.out.println("RCV (model): "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("RCV (model): "+messageName+" | No data sent");
    }
  }

  /**
   * Initialize the model here, subscribe to any required messages and
   * load with some initial to do list items
   * 
   */
  public void init() {
    mvcMessaging.subscribe("controller:changeButton", this);
  }

  /** 
   * Find item by id number
   * @param id ID of the item to fine
   * @return The item with id that was found in the list, null if the ID wasn't found
   */
  private ToDoItem find(int id) {
    ToDoItem foundItem = null;
    for (ToDoItem item : toDoList) {
      if (item.getId() == id) {
        foundItem = item;
        break;    // Break the for loop when item is found
      }
    }  
    return foundItem;
  }
  
  /**
   * Get an item with the supplied ID
   * @param id ID of the item to return
   * @return The desired item; null if item ID is not found
   */
  public ToDoItem getItem(int id) {
    return find(id);
  }
  
  /**
   * Get the to do list
   * @return The to do list
   */
  public ArrayList getItems() {
    return toDoList;
  }
  
  // Functions to add, delete, and edit a to do item
  /** 
   * Add a new to do item to the list
   * @param description To do item description
   * @return Item added
   */
  public ToDoItem add(String description) {
    ToDoItem item = new ToDoItem(nextId, description);
    toDoList.add(item);
    nextId++;
    return item;
  }
  
  /**
   * Delete a to do item
   * @param id ID of the item to delete
   * @return True if the item was found and removed
   */
  public boolean delete(int id) {
    ToDoItem item = find(id);
    return toDoList.remove(item);
  }

  /** 
   * Mark a to do item as not complete
   * @param id ID of the item to change
   * @return True if item found and status changed
   */
  public boolean setNotComplete(int id) {
    ToDoItem item = find(id);
    if (item != null) {
      item.notCompleted();
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Mark a to do item as complete
   * @param id ID of the item to change
   * @return True if item found and status changed
   */
  public boolean setComplete(int id) {
    ToDoItem item = find(id);
    if (item != null) {
      item.completed();
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Change the description of a to do item
   * @param id ID of the item to change
   * @param newDescription New description
   * @return True if item found and status changed
   */
  public boolean changeDescription(int id, String newDescription) {
    ToDoItem item = find(id);
    if (item != null) {
      item.setDescription(newDescription);
      return true;
    } else {
      return false;
    }
  }

}
