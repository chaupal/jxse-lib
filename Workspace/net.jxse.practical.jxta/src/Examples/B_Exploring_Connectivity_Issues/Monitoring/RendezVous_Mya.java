
package Examples.B_Exploring_Connectivity_Issues.Monitoring;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.jxse.osgi.compat.AbstractJP2PCompatibility;
import net.jxse.osgi.compat.IJxtaNode;
import net.jxta.exception.ConfiguratorException;
import net.jxta.exception.JxtaException;
import net.jxta.exception.PeerGroupException;
import net.jxta.id.IDFactory;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupID;
import net.jxta.platform.JxtaApplication;
import net.jxta.platform.NetworkConfigurator;
import net.jxta.platform.NetworkManager;

/**
 * Simple RENDEZVOUS peer connecting via the NetPeerGroup.
 */
public class RendezVous_Mya extends AbstractJP2PCompatibility<Object> {

    // Static

    public static final String Name_RDV = "RENDEZVOUS";
    public static final PeerID PID_RDV = IDFactory.newPeerID(PeerGroupID.defaultNetPeerGroupID, Name_RDV.getBytes());
    public static final int TcpPort_RDV = 9711;
    public static final File ConfigurationFile_RDV = new File("." + System.getProperty("file.separator") + Name_RDV);

    public RendezVous_Mya() {
		super(Name_RDV);
	}

    /**
     * @param args the command line arguments
     */
    public void main(String[] args) {

        try {

            // Removing any existing configuration?
            NetworkManager.RecursiveDelete(ConfigurationFile_RDV);

            // Creation of the network manager
            final NetworkManager MyNetworkManager = JxtaApplication.getNetworkManager(
                    NetworkManager.ConfigMode.RENDEZVOUS,
                    Name_RDV, ConfigurationFile_RDV.toURI());
            IJxtaNode<Object> root = super.createRoot( MyNetworkManager );

            // Retrieving the network configurator
            NetworkConfigurator MyNetworkConfigurator = MyNetworkManager.getConfigurator();
            MyNetworkConfigurator.setPrincipal( Name_RDV );
            
            // Setting Configuration
            MyNetworkConfigurator.setUseMulticast(false);

            MyNetworkConfigurator.setTcpPort(TcpPort_RDV);
            MyNetworkConfigurator.setTcpEnabled(true);
            MyNetworkConfigurator.setTcpIncoming(true);
            MyNetworkConfigurator.setTcpOutgoing(true);

            // Setting the Peer ID
            MyNetworkConfigurator.setPeerID(PID_RDV);

            // Starting the JXTA network
            PeerGroup NetPeerGroup = MyNetworkManager.startNetwork();

            // Starting the connectivity monitor
            new ConnectivityMonitor(NetPeerGroup);

            // Stopping the network asynchronously
            ConnectivityMonitor.TheExecutor.schedule(
                new DelayedJxtaNetworkStopper(
                    MyNetworkManager,
                    "Click to stop " + Name_RDV,
                    "Stop"),
                0,
                TimeUnit.SECONDS);

        } catch (IOException Ex) {

            System.err.println(Ex.toString());

        } catch (PeerGroupException Ex) {

            System.err.println(Ex.toString());

        } catch (ConfiguratorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JxtaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @Override
    public void deactivate() {
    	NetworkManager MyNetworkManager = (NetworkManager) super.getRoot().getModule();
    	MyNetworkManager.stopNetwork();
    }   
}
