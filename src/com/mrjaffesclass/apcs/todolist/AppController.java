package com.mrjaffesclass.apcs.todolist;

import com.mrjaffesclass.apcs.messenger.*;
import java.util.Date;

/**
 *
 * To do list controller. The controller will bootstrap the app. "Bootstrap"
 * means to create and load all the other classes in the app.
 *
 * @authors Rachel Schmeltzer, Rebekah Leslie, and Ann Tran
 * @version 1.0
 */
public class AppController implements MessageHandler {

    /**
     * messenger: This class handles the messaging between classes mainView:
     * This is the class for the user interface appModel: This is the class that
     * contains the to do list data
     */
    private final Messenger messenger;
    private final MainView mainView;
    private final AppModel appModel;

    /**
     * Controller constructor The Controller is responsible for creating the
     * MainView and the AppModel that it will be controlling. The messenger
     * object is passed to the view and the model and is used as a messenger
     * class between the Controller, AppModel and MainView without needing a
     * persistent variable reference to the MainView and AppModel.
     *
     * Remember, to achieve "separation of concerns" you want the three
     * components insulated from each other so that one class works
     * independently of the others.
     */
    public AppController() {
        // Create the messenger
        messenger = new Messenger();

        // Create and initialize the main view and set it visible
        mainView = new MainView(messenger);
        mainView.init();
        mainView.setVisible(true);

        // Create and initialize the app model
        appModel = new AppModel(messenger);
        appModel.init();
    }

    /**
     * Initialize the appController here Add some sample data (which we would
     * remove if we were loading data from a source in the "cloud") Send the
     * 'notify' message so other modules know when to start
     */
    public void init() {
        addSampleItems();
        messenger.notify("ready");
    }

    @Override
    public void messageHandler(String messageName, Object messagePayload) {
        // The appController doesn't need to handle any messages
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
            "Deposit paycheck",};

        Date[] initialDueDates = {
            new Date(115, 3, 7), //1447574400000),  //Apr 7, 2015 
            new Date(115, 5, 15), //1433142000000),  //June 15, 2015
            new Date(115, 3, 9), //1434351600000),  //Apr 9, 2015
            new Date(115, 5, 1), //1438412400000),  //June 1, 2015
            new Date(115, 4, 1), //1443682800000),  //May 1, 2015
            new Date(115, 3, 11) //1441090800000)   //Apr 11, 2015
        };

        // Loop through initialData and create items to add to the appModel
        int i = 0;
        for (String description : initialData) {
            ToDoItem item = new ToDoItem(-1, description, false, initialDueDates[i]);
            appModel.putItem(item);
            i++;
        }
    }

    /**
     * Remember that all Java programs are started with the main method in one
     * of the program's classes.
     *
     * @param args No arguments are necessary for this to do list application
     */
    public static void main(String[] args) {
        String opSysName = System.getProperty("os.name").toLowerCase();

        // This will set the app title in the Mac main menu
        if (opSysName.contains("mac")) {
            // to set the name of the app in the Mac App menu:
            System.setProperty("apple.awt.application.name", "My To Do List");
            // to show the menu bar at the top of the screen:
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            // to show a more mac-like file dialog box
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
            // underlying look-and-feel:
            javax.swing.UIManager.getInstalledLookAndFeels();
        }

        // Bootstrap the app here.  The appController instantiates the model and
        // the view. 
        AppController controller = new AppController();
        controller.init();
    }

}
