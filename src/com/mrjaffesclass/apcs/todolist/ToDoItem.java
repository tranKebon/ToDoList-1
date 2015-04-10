package com.mrjaffesclass.apcs.todolist;

import java.util.Date;

/**
 * To do item
 *
 * @authors Rachel Schmeltzer, Rebekah Leslie, and Ann Tran
 * @version 1.0
 */
public class ToDoItem implements Comparable<ToDoItem> {

    /**
     * id: ID number of to do item. Assigned when added to list description:
     * Description of to do item done: True if to do item is complete
     */
    public static int compareMethod = 1;
    private int id;
    private String description;
    private boolean done;
    private Date itemdate;

    /**
     * Constructor with done set to false in constructor
     *
     * @param _id ID number of to do item
     * @param _description Description of to do item
     */
    public ToDoItem(int _id, String _description) {
        description = _description;
        id = _id;
        done = false;     // Default to not completed
        itemdate = new Date();
    }

    /**
     * Constructor
     *
     * @param _id ID number of to do item
     * @param _description Description of to do item
     * @param _done Done flag
     */
    public ToDoItem(int _id, String _description, boolean _done) {
        description = _description;
        id = _id;
        done = _done;     // Default to not completed
        itemdate = new Date();
    }

    /**
     * Constructor
     *
     * @param _id ID number of to do item
     * @param _description Description of to do item
     * @param _done Done flag
     * @param _date date of to do item
     */
    public ToDoItem(int _id, String _description, boolean _done, Date _date) {
        description = _description;
        id = _id;
        done = _done;     // Default to not completed
        itemdate = _date;
    }

    /**
     * Get the to do item description
     *
     * @return The description of to do item
     */
    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return itemdate;
    }

    public void setDate(Date iDate) {
        this.itemdate = iDate;
    }

    /**
     * Set the to do item description
     *
     * @param description The value to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the done flag
     *
     * @return The done flag
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Set the done flag
     *
     * @param done The value to be set
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Toggles the done flag
     */
    public void toggleDone() {
        this.done = !this.done;
    }

    /**
     * Get the to do item ID
     *
     * @return ID of the to do item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the to do item. Can only be called from inside this class
     *
     * @param id ID value to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Transfer the description and done flag of another to do item into this
     * one
     *
     * @param anotherItem Item whose data values we are copying
     */
    public void merge(ToDoItem anotherItem) {
        this.setDescription(anotherItem.getDescription());
        this.setDone(anotherItem.isDone());
    }

    @Override
    /**
     * Compares the Item Dates inside the table to the other dates so they would get sorted
     * 
     */
    public int compareTo(ToDoItem compareToDoItem) {
        int retVal;   // need to return an int but getTime is a long
        long thisOne = this.getDate().getTime();
        long otherOne = compareToDoItem.getDate().getTime();
        /* For Ascending order*/

        if ((thisOne - otherOne) > 0) {
            retVal = 1;
        } else if ((thisOne - otherOne) < 0) {
            retVal = -1;
        } else {
            retVal = 0;
        }
        return compareMethod * retVal;
    }
}
