package gov.ca.cwds.cals.persistence.dao;

import gov.ca.cwds.cals.persistence.DatabaseHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LisFacFileDaoTest {

    @Before
    public void setUp() throws Exception {
        DatabaseHelper.createFasDdl();
    }

    @Test
    public void testFindById() {

    }

    @After
    public void tearDown() throws Exception {

    }
}