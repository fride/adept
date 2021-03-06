package adept.core.resolution

import adept.core.models._
import org.scalatest._
import adept.test._

class MatcherTest extends FunSuite  {
  import VariantsLoaderEngineTester._
  
  test("one to one matching") {
    testMatches(Set(Attribute("version", Set("1.0"))), Set(Constraint("version", Set("1.0"))), true)
  }

  test("multi attributes with one matching constraint") {
    testMatches(Set(Attribute("version", Set("1.0")), Attribute("binary-version", Set("1.0"))), Set(Constraint("version", Set("1.0"))), true)
  }

  test("one to one with no matching constraints") {
    testMatches(Set(Attribute("version", Set("1.0"))), Set(Constraint("nomatch", Set("1.0"))), false)
  }

  test("multiple constraints, one not matching") {
    testMatches(Set(Attribute("version", Set("1.0"))), Set(Constraint("version", Set("1.0")), Constraint("binary-version", Set("1.0"))), false)
  }

  test("multiple attributes and constraints matching") {
    testMatches(Set(Attribute("version", Set("1.0")), Attribute("binary-version", Set("1.0"))), Set(Constraint("version", Set("1.0")), Constraint("binary-version", Set("1.0"))), true)
  }

  test("constraint conflict (no matches possible)") {
    testMatches(Set(Attribute("version", Set("1.0"))), Set(Constraint("version", Set("1.0")), Constraint("version", Set("2.0"))), false)
  }
}
