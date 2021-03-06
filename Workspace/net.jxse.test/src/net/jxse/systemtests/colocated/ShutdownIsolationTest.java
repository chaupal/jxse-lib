package net.jxse.systemtests.colocated;

import static org.junit.Assert.*;

import net.jxta.impl.platform.JxtaApplication;
import net.jxta.impl.platform.NetworkManager;
import net.jxta.impl.platform.NetworkManager.ConfigMode;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

@Ignore("Must fix")
public class ShutdownIsolationTest {

	@Rule
    public TemporaryFolder tempStorage = new TemporaryFolder();

    private NetworkManager aliceManager;
    private NetworkManager bobManager;
    private NetworkManager cliveManager;

    @Before
    public void initPeers() throws Exception {
        //aliceManager = new NetworkManager(ConfigMode.ADHOC, "alice", tempStorage.newFolder("alice").toURI());
        //bobManager = new NetworkManager(ConfigMode.ADHOC, "bob", tempStorage.newFolder("bob").toURI());
        //cliveManager = new NetworkManager(ConfigMode.ADHOC, "clive", tempStorage.newFolder("clive").toURI());
        
        aliceManager = JxtaApplication.getNetworkManager(ConfigMode.ADHOC, "alice", tempStorage.newFolder("alice").toURI());
        bobManager = JxtaApplication.getNetworkManager(ConfigMode.ADHOC, "bob", tempStorage.newFolder("bob").toURI());
        cliveManager = JxtaApplication.getNetworkManager(ConfigMode.ADHOC, "clive", tempStorage.newFolder("clive").toURI());

        aliceManager.startNetwork();
        bobManager.startNetwork();
        cliveManager.startNetwork();
    }

    @After
    public void killPeers() throws Exception {
        if(aliceManager.isStarted()) {
        	aliceManager.stopNetwork();
        }

        if(bobManager.isStarted()) {
        	bobManager.stopNetwork();
        }
    }

    @Test
    public void testShutdownIsolation() throws Exception {
        bobManager.stopNetwork();

        assertTrue(aliceManager.isStarted());
        assertFalse(bobManager.isStarted());
        assertTrue(cliveManager.isStarted());

        SystemTestUtils.testPeerCommunication(aliceManager, cliveManager);
    }
}
