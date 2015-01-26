package net.jxse.osgi.test.modulemanager;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import junit.framework.JUnit4TestAdapter;
import net.jxse.osgi.test.Component;
import net.jxta.impl.loader.JxtaLoaderModuleManager;
import net.jxta.peergroup.core.Module;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JxtaLoaderModuleManagerTest {

    static final Logger LOG =
            Logger.getLogger(JxtaLoaderModuleManagerTest .class.getName());

    public static junit.framework.Test suite() { 
        return new JUnit4TestAdapter(JxtaLoaderModuleManagerTest.class); 
    }

    private JxtaLoaderModuleManager<Module> manager;
    
    @BeforeClass
    public static void setupClass() throws Exception {
        LOG.info("============ Begin setupClass");
    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        LOG.info("============ Begin tearDownClass");
    }
    
    @Before
    public void setup() throws Exception {
        LOG.info("============ Begin setup");
        manager = Component.getModuleManager();
    }
    
    @After
    public void tearDown() throws Exception {
        LOG.info("============ Begin tearDown");
        manager = null;
        Thread.sleep(300);
        LOG.info("============ End tearDown");
    }
    
    @Test
    public void testBuild(){
    	PlatformDescriptor descriptor = new PlatformDescriptor();
    	assertTrue( manager.canBuild(descriptor));
    }
}
