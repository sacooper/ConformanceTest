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
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.setTotalQtrs(0);
    klazz.setCurQtrs(0);
    klazz.setAllowVend(false);
  }
  @Test
  public void testCase0() {
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    klazz.setAllowVend(true);
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.vend();
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.setTotalQtrs(klazz.getTotalQtrs() + 2);
    klazz.setCurQtrs(klazz.getCurQtrs() - 2);
  }

  @Test
  public void testCase1() {
    klazz.returnQtrs();
    assertEquals(klazz.getStateFullName(), "empty");
  }

  @Test
  public void testCase2() {
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.reset();
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.setTotalQtrs(0);
  }

  @Test
  public void testCase3() {
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    klazz.returnQtrs();
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.setCurQtrs(0);
  }

  @Test
  public void testCase4() {
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.reset();
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.setTotalQtrs(0);
    klazz.setCurQtrs(0);
  }

  @Test
  public void testCase5() {
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    klazz.setAllowVend(true);
    klazz.returnQtrs();
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.setCurQtrs(0);
    klazz.setAllowVend(false);
  }

  @Test
  public void testCase6() {
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    klazz.setAllowVend(true);
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.reset();
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.setTotalQtrs(0);
    klazz.setCurQtrs(0);
    klazz.setAllowVend(false);
  }

  @Test
  public void testCase7() {
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    klazz.setAllowVend(true);
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
  }

  @Test
  public void testCase8() {
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    klazz.setAllowVend(true);
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.vend();
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.setTotalQtrs(klazz.getTotalQtrs() + 2);
    klazz.setCurQtrs(0);
    klazz.setAllowVend(false);
  }

  @Test
  public void testCase9() {
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    assertEquals(klazz.getStateFullName(), "empty");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.addQtr();
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.setCurQtrs(klazz.getCurQtrs() + 1);
    klazz.setAllowVend(true);
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    assertEquals(klazz.getStateFullName(), "allowed");
    klazz.vend();
    assertEquals(klazz.getStateFullName(), "notAllowed");
    klazz.setTotalQtrs(klazz.getTotalQtrs() + 2);
    klazz.setCurQtrs(1);
    klazz.setAllowVend(false);
  }

}
