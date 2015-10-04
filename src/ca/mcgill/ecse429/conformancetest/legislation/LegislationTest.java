package ca.mcgill.ecse429.conformancetest.legislation;
import ca.mcgill.ecse429.conformancetest.legislation.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LegislationTest {
  private Legislation klazz;

  @Before
  public void setUp() throws Exception {
    klazz = new Legislation();
    assertEquals("inPreparation", klazz.getStateFullName());
  }
  @Test
  public void testCase0() {
    klazz.introduceInHouse();
    assertEquals("inHouseOfCommons", klazz.getStateFullName());
  }

  @Test
  public void testCase1() {
    klazz.introduceInSenate();
    assertEquals("inSenate", klazz.getStateFullName());
    klazz.setIsCommonsBill(true);
    klazz.votePasses();
    assertEquals("finalized", klazz.getStateFullName());
  }

  @Test
  public void testCase2() {
    klazz.introduceInSenate();
    assertEquals("inSenate", klazz.getStateFullName());
    klazz.voteFails();
    assertEquals("inPreparation", klazz.getStateFullName());
  }

  @Test
  public void testCase3() {
    klazz.introduceInSenate();
    assertEquals("inSenate", klazz.getStateFullName());
    klazz.setIsCommonsBill(false);
    klazz.votePasses();
    assertEquals("inHouseOfCommons", klazz.getStateFullName());
    klazz.setIsCommonsBill(false);
    klazz.votePasses();
    assertEquals("finalized", klazz.getStateFullName());
  }

  @Test
  public void testCase4() {
    klazz.introduceInSenate();
    assertEquals("inSenate", klazz.getStateFullName());
    klazz.setIsCommonsBill(false);
    klazz.votePasses();
    assertEquals("inHouseOfCommons", klazz.getStateFullName());
    klazz.voteFails();
    assertEquals("inPreparation", klazz.getStateFullName());
  }

  @Test
  public void testCase5() {
    klazz.introduceInSenate();
    assertEquals("inSenate", klazz.getStateFullName());
    klazz.setIsCommonsBill(false);
    klazz.votePasses();
    assertEquals("inHouseOfCommons", klazz.getStateFullName());
    klazz.setIsCommonsBill(true);
    klazz.votePasses();
    assertEquals("inSenate", klazz.getStateFullName());
  }

}