package com.mrjaffesclass.apcs.todolist;

import java.util.*;
import com.mrjaffesclass.apcs.messages.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageMailbox {

  // Messaging system for the MVC
  private final Messaging mvcMessaging;

  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messaging messages) {
    mvcMessaging = messages;
  }
  
  /**
   * Initialize the model here and subscribe to any required messages
   */
  public void init() {
    mvcMessaging.subscribe("controller:changeButton", this);
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    
    // Print debug messages to the console. Remove these when our app is working
    if (messagePayload != null) {
      System.out.println("RCV (model): "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("RCV (model): "+messageName+" | No data sent");
    }
  }

}
