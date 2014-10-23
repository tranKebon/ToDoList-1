package com.mrjaffesclass.apcs.todolist;

import java.util.*;
import com.mrjaffesclass.apcs.messenger.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class AppModel implements MessageHandler {

  // Instance variables
  // ***** MOVE THIS INTO THE MESSAGEMAILBOX CLASS!!
  private final Messenger messenger;
  // List of to do items
  private final ArrayList<ToDoItem> toDoList;   
  // Keeps track of the next item's ID number
  private int nextId = 0;                 
  
  /**
   * Model constructor: Create the data representation of the program
   * @param _messenger Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public AppModel(Messenger _messenger) {
    messenger = _messenger;
    // We start with an empty list
    toDoList = new ArrayList<>();
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    // Grab the to do item
    ToDoItem item = (ToDoItem)messagePayload;
    
    // Process the message
    switch (messageName) {
      // View has asked for the list of to do items
      // Send back the items
      case "getItems":
        messenger.notify("items", this.getItems(), true);
        break;
        
      // Controller has asked for an individual item
      // Find the item and send it back
      case "getItem":
        messenger.notify("item", this.getItem((int)messagePayload), true);
        break;
        
      case "saveItem":
        putItem(item);
        messenger.notify("saved", null, true);
        messenger.notify("items", this.getItems(), true);
        break;
        
      case "deleteItem":
        this.deleteItem(item);
        messenger.notify("saved", null, true);
        messenger.notify("items", this.getItems(), true);
        break;
        
      case "removeCompletedItems":
        removeCompletedItems();
        messenger.notify("saved");
        messenger.notify("items", this.getItems());
    }
  }

  /**
   * Initialize the model here, subscribe to any required messages and
   * load with some initial to do list items
   * 
   */
  public void init() {
    messenger.subscribe("getItems", this);
    messenger.subscribe("getItem", this);
    messenger.subscribe("saveItem", this);
    messenger.subscribe("deleteItem", this);
    messenger.subscribe("removeCompletedItems", this);
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
   * @param item To do item to add
   * @return Item added
   */
  public ToDoItem putItem(ToDoItem item) {
    if (item.getId() == -1) {
      // Item is new, add it to the list
      item.setId(nextId);
      toDoList.add(item);
      nextId++;
    } else {
      // Item already exists, modify the fields
      ToDoItem storedItem = this.find(item.getId());
      storedItem.merge(item);
    }
    return item;
  }
  
  /**
   * Delete a to do item
   * @param id ID of the item to delete
   * @return True if the item was found and removed
   */
  public boolean deleteItem(int id) {
    ToDoItem item = find(id);
    return toDoList.remove(item);
  }

  /**
   * Delete a to do item
   * @param item ToDoItem to delete
   * @return true if the item was found and removed
   */
  public boolean deleteItem(ToDoItem item) {
    return deleteItem(item.getId());
  }
  
  /**
   * Removes all completed items from the to do list
   */
  public void removeCompletedItems() {
    ArrayList<ToDoItem> newList = new ArrayList<>();
    for (ToDoItem item : toDoList) {
      if (!item.isDone()) {
        newList.add(item);
      }
    }
    toDoList.clear();
    for (ToDoItem item : newList) {
      toDoList.add(item);
    }
  }
}
