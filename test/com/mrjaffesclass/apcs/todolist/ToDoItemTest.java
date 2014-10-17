package com.mrjaffesclass.apcs.todolist;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the ToDoItem class
 * 
 * @author Roger Jaffe
 * @version 1.0
 */
public class ToDoItemTest {
    
  public ToDoItemTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getDescription method, of class ToDoItem.
   */
  @Test
  public void testGetDescription() {
    ToDoItem item = new ToDoItem(2, "Test description");
    assertEquals("getDescription", "Test description", item.getDescription());
  }

  /**
   * Test of setDescription method, of class ToDoItem.
   */
  @Test
  public void testSetDescription() {
    ToDoItem item = new ToDoItem(2, "Test description");
    item.setDescription("A new description");
    assertEquals("setDescription", "A new description", item.getDescription());
  }

  /**
   * Test of isDone method, of class ToDoItem.
   */
  @Test
  public void testNotCompleted() {
    ToDoItem item = new ToDoItem(2, "Test description");
    item.completed();
    item.notCompleted();
    assertFalse("notCompleted", item.isCompleted());
  }

  /**
   * Test of setDone method, of class ToDoItem.
   */
  @Test
  public void testCompleted() {
    ToDoItem item = new ToDoItem(2, "Test description");
    item.completed();
    assertTrue("completed", item.isCompleted());
  }

  /**
   * Test of getId method, of class ToDoItem.
   */
  @Test
  public void testGetId() {
    ToDoItem item = new ToDoItem(22, "Test description");
    assertEquals("getId", 22, item.getId());
  }

}
