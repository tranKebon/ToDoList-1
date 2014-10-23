package com.mrjaffesclass.apcs.todolist;
import com.mrjaffesclass.apcs.messenger.*;

/**
 * 
 * To do list controller.  We will receive messages and/or message payload
 * from the view and the model to direct the to do list to do our bidding.
 * mwahaha...
 *
 * @author Roger Jaffe
 * @version 1.0
 */
public class AppController implements MessageHandler {

  private final Messenger messenger;
  private final MainView view;
  private final AppModel appModel;

  /**
   * Controller constructor The Controller is responsible for creating the MainView
   * and the AppModel that it will be controlling. The mvcMessaging object is
   * passed to the view and the model and is used as a local messenger
   * between the Controller, AppModel and MainView without have direct access to the
   * MainView and AppModel.  Remember, you want the three components separated so
   * that one class works independently of the others.
   */
  public AppController() {
    // Create the local messaging class
    messenger = new Messenger();

    // Create the view and set it visible
    view = new MainView(messenger);   
    view.init();
    view.setVisible(true);

    // Create the model
    appModel = new AppModel(messenger);  
    appModel.init();    
  }

  /**
   * Initialize the model here and subscribe
   * to any required messages 
   */
  public void init() {
    addSampleItems();
    messenger.notify("ready");
  }

  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    // The appController doesn't handle any messages
    switch (messageName) {
    }
  }
  
   /**
   * Add some initial items to our to do list
   */
  public void addSampleItems() {
    String[] initialData = {
      "Do APCS project", 
      "Finish English paper", 
      "This is a very long to do item that has to be done soon!",
      "Proofread resume",
      "Get gas in the car",
      "Deposit paycheck",
    };
    
    // Loop through the initialData and create items to add to the appModel
    for (String description : initialData) {
      ToDoItem item = new ToDoItem(-1, description);
      appModel.putItem (item);
    }
  }

  /**
   * Bootstrap the app.  The appController instantiates the model and the view
   * @param args No arguments are necessary for this to do list application
   */
  public static void main(String[] args) {
    AppController controller = new AppController();
    controller.init();
  }
  
}
