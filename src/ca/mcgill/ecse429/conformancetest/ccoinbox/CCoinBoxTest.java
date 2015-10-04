package ca.mcgill.ecse429.conformancetest.ccoinbox;
import ca.mcgill.ecse429.conformancetest.ccoinbox.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CCoinBoxTest {
  private CCoinBox klazz;

  @Before
  public void setUp() throws Exception {
    klazz = new CCoinBox();
    assertEquals("empty", klazz.getStateFullName());
  }
  @Test
  public void testCase0() {
    klazz.addQtr();
    assertEquals("notAllowed", klazz.getStateFullName());
    klazz.addQtr();
    assertEquals("allowed", klazz.getStateFullName());
    klazz.setCurQtrs(3 + 1);
    klazz.vend();
    assertEquals("allowed", klazz.getStateFullName());
  }

  @Test
  public void testCase1() {
    klazz.returnQtrs();
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase2() {
    klazz.reset();
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase3() {
    klazz.addQtr();
    assertEquals("notAllowed", klazz.getStateFullName());
    klazz.returnQtrs();
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase4() {
    klazz.addQtr();
    assertEquals("notAllowed", klazz.getStateFullName());
    klazz.reset();
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase5() {
    klazz.addQtr();
    assertEquals("notAllowed", klazz.getStateFullName());
    klazz.addQtr();
    assertEquals("allowed", klazz.getStateFullName());
    klazz.returnQtrs();
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase6() {
    klazz.addQtr();
    assertEquals("notAllowed", klazz.getStateFullName());
    klazz.addQtr();
    assertEquals("allowed", klazz.getStateFullName());
    klazz.reset();
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase7() {
    klazz.addQtr();
    assertEquals("notAllowed", klazz.getStateFullName());
    klazz.addQtr();
    assertEquals("allowed", klazz.getStateFullName());
    klazz.addQtr();
    assertEquals("allowed", klazz.getStateFullName());
  }

  @Test
  public void testCase8() {
    klazz.addQtr();
    assertEquals("notAllowed", klazz.getStateFullName());
    klazz.addQtr();
    assertEquals("allowed", klazz.getStateFullName());
    klazz.setCurQtrs(2);
    klazz.vend();
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase9() {
    klazz.addQtr();
    assertEquals("notAllowed", klazz.getStateFullName());
    klazz.addQtr();
    assertEquals("allowed", klazz.getStateFullName());
    klazz.setCurQtrs(3);
    klazz.vend();
    assertEquals("notAllowed", klazz.getStateFullName());
  }

}