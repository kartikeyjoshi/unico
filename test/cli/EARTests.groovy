import grails.test.AbstractCliTestCase

class EARTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testEAR() {

        execute(["ear"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}
