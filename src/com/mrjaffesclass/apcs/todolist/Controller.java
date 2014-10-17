package com.mrjaffesclass.apcs.todolist;
import com.mrjaffesclass.apcs.messages.*;

/**
 * 
 * To do list controller.  We will receive messages and/or message payload
 * from the view and the model to direct the to do list to do our bidding.
 * mwahaha...
 *
 * @author Roger Jaffe
 * @version 1.0
 */
public class Controller implements MessageMailbox {

  private final Messaging mvcMessaging;
  private View view;
  private Model model;

  /**
   * Controller constructor The Controller is responsible for creating the View
   * and the Model that it will be controlling. The mvcMessaging object is
   * passed to the view and the model and is used as a local messenger
   * between the Controller, Model and View without have direct access to the
   * View and Model.  Remember, you want the three components separated so
   * that one class works independently of the others.
   *
   * Messages that can be received in the Controller:
   *  view:toggleButtonClick (sent by the View when the toggle button is clicked)
   *  view:buttonClick (sent by the View when the regular button is clicked)
   *  view:changeButton (sent by the View when the Up or Down buttons are clicked)
   * Message that are sent from the Controller:
   *  controller:changeButton (sent by the Controller to notify the Model to change 
   *    the value of a Model variable
   */
  public Controller() {
    // Create the local messaging class
    mvcMessaging = new Messaging();

    // Create the view and set it visible
    view = new View(mvcMessaging);    // This creates our view
    view.init();
    view.setVisible(true);

    // Create the model
    model = new Model(mvcMessaging);  // This creates our model
    model.init();
    
    // Add some sample to do items to the model
  }

  /**
   * Initialize the model here and subscribe
   * to any required messages
   * 
   * "this" refers to this controller object.
   */
  public void init() {
//    mvcMessaging.subscribe("view:toggleButtonClick", this);
    addSampleItems();
  }

  @Override
  public void messageHandler(String messageName, Object messagePayload) {

    // Debug messages here -- we can remove them when our app is working properly
    if (messagePayload != null) {
      System.out.println("RCV (controller): "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("RCV (controller): "+messageName+" | No data sent");
    }
  }
  
   /**
   * Add some initial items to our to do list
   */
  public void addSampleItems() {
    String[] initialData = {
      "Do APCS project", 
      "Finish English paper", 
      "Proofread resume",
      "Get gas in the car",
      "Deposit paycheck"
    };
    for (String description : initialData) {
      model.add (description);
    }
  }

}
