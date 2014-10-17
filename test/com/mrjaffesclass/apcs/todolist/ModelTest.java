package com.mrjaffesclass.apcs.todolist;

import com.mrjaffesclass.apcs.messages.*;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the Model class
 * @author Roger Jaffe
 * @version 1.0
 */
public class ModelTest {
  
  public Model model;
  public String[] initialData = {
    "Do APCS project", 
    "Finish English paper", 
    "Proofread resume",
    "Get gas in the car",
    "Deposit paycheck"
  };
  public ArrayList<ToDoItem> testArrayList;
  
  public ModelTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    model = new Model(new Messaging());
    model = this.addSampleItems(model);
    
    this.testArrayList = new ArrayList<>();
    for (int i=0; i<initialData.length; i++) {
      testArrayList.add(new ToDoItem(i, initialData[i]));
    }
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of messageHandler method, of class Model.
   */
  @Test
  public void testMessageHandler() {
    assertTrue("testMessageHandler is not tested", true);
  }

  /**
   * Test of init method, of class Model.
   */
  @Test
  public void testInit() {
    assertTrue("init is not tested", true);
  }

  /**
   * Test of getItem method, of class Model.
   */
  @Test
  public void testGetItem() {
    ToDoItem item = model.getItem(3);
    assertEquals("testGetItem", "Get gas in the car", item.getDescription());
  }

  /**
   * Test of getItems method, of class Model.
   */
  @Test
  public void testGetItems() {
    ArrayList<ToDoItem> ar = model.getItems();
    assertEquals("testGetItems length of ArrayList", 5, ar.size());
  }

  /**
   * Test of add method, of class Model.
   */
  @Test
  public void testAdd1() {
    ToDoItem newItem = model.add("This is added item 1");
    assertEquals("testAdd1: Item returned", "This is added item 1", newItem.getDescription());
  }
  
  /** 
   * Test of add method on found new item
   */
  @Test
  public void testAdd2() {
    ToDoItem newItem = model.add("This is added item 2");
    newItem = model.getItem(newItem.getId());
    assertEquals("testAdd2: Item returned", "This is added item 2", newItem.getDescription());
  }
  

  /**
   * Test of delete method, of class Model.
   */
  @Test
  public void testDelete1() {
    boolean removed = model.delete(1);
    ToDoItem newItem = model.getItem(1);
    assertNull("testDelete: Item removed", newItem);
  }

  /**
   * Test of delete method, of class Model.
   */
  @Test
  public void testDelete2() {
    boolean removed = model.delete(1);
    int size = model.getItems().size();
    assertEquals("testDelete: Correct size of list", 4, size);
  }

  /**
   * Test of setComplete method, of class Model.
   */
  @Test
  public void testSetCompleteCheckItem() {
    model.setComplete(2);
    ToDoItem item = model.getItem(2);
    assertTrue("testSetCompleteCheckItem: Set item as completed", item.isCompleted());
  }

  /**
   * Test of setComplete method, of class Model.
   */
  @Test
  public void testSetCompleteCheckOtherItemUnchanged() {
    model.setComplete(2);
    ToDoItem item = model.getItem(3);
    assertFalse("testSetCompleteCheckOtherItemUnchanged: No change to another item", item.isCompleted());
  }

  /**
   * Test of setNotComplete method, of class Model.
   */
  @Test
  public void testSetNotCompleteCheckItem() {
    model.setComplete(2);
    model.setNotComplete(2);
    ToDoItem item = model.getItem(2);
    assertFalse("testSetNotCompleteCheckItem", item.isCompleted());
 }

  /**
   * Test of setNotComplete method, of class Model.
   */
  @Test
  public void testSetNotCompleteCheckOtherItemUnchanged() {
    model.setComplete(3);
    model.setComplete(2);
    model.setNotComplete(2);
    ToDoItem item = model.getItem(3);
    assertTrue("testSetNotCompleteCheckOtherItemUnchanged", item.isCompleted());
 }

  /**
   * Test of changeDescription method, of class Model.
   */
  @Test
  public void testChangeDescription() {
    model.changeDescription(1, "This is a new description");
    ToDoItem item = model.getItem(1);
    assertEquals("testChangeDescription", "This is a new description", item.getDescription());
  }
  
   /**
   * Add some initial items to our to do list
   * @param model Model under test
   * @return Model returned
   */
  public Model addSampleItems(Model model) {
    for (String description : initialData) {
      model.add (description);
    }
    return model;
  }
  
}
