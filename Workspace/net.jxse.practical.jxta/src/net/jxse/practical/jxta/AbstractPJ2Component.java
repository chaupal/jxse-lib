package net.jxse.practical.jxta;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Platform;

import Examples.A_JXTA_Connection_And_Local_Configuration._100_Starting_And_Stopping_JXTA_Example;
import Examples.A_JXTA_Connection_And_Local_Configuration._110_Creating_A_Local_Configuration_Example;
import Examples.A_JXTA_Connection_And_Local_Configuration._120_Retrieving_Modifying_And_Saving_An_Existing_Configuration_Example;
import Examples.A_JXTA_Connection_And_Local_Configuration._150_Configuration_Objects;
import Examples.B_Exploring_Connectivity_Issues.Edge_Anna;
import Examples.B_Exploring_Connectivity_Issues.Edge_Chihiro;
import Examples.B_Exploring_Connectivity_Issues.RendezVous_Aminah;
import Examples.B_Exploring_Connectivity_Issues.RendezVous_Jack;
import Examples.B_Exploring_Connectivity_Issues.Monitoring.ConnectivityMonitor;
import Examples.B_Exploring_Connectivity_Issues.Monitoring.DelayedJxtaNetworkStopper;
import Examples.B_Exploring_Connectivity_Issues.Monitoring.Edge_Gina;
import Examples.B_Exploring_Connectivity_Issues.Monitoring.Relay_Robert;
import Examples.B_Exploring_Connectivity_Issues.Monitoring.RendezVous_Mya;
import Examples.C_Peers_And_PeerGroups._200_Creating_IDs_Example;
import Examples.C_Peers_And_PeerGroups._210_Creating_A_New_Peer_Example;
import Examples.C_Peers_And_PeerGroups._220_Creating_A_Custom_PeerGroup_Example;
import Examples.D_Discovering_Resources.Edge_Maxime_The_Socializer;
import Examples.D_Discovering_Resources._300_Retrieving_And_Flushing_Local_Advertisements_Example;
import net.osgi.jxse.AbstractJxseComponent;

public class AbstractPJ2Component extends AbstractJxseComponent {

	public enum Examples{
		//Examples.A_JXTA_Connection_And_Local_Configuration
		A_100_Starting_And_Stopping_JXTA,
		A_110_Creating_A_Local_Configuration,
		A_120_Retrieving_Modifying_And_Saving_An_Existing_Configuration,
		A_150_Configuration_Objects,
		
		//Examples.B_Exploring_Connectivity_Issues
		B_Exploring_Connectivity_Issues,
		B_Edge_Anna,
		B_Edge_Chihiro,
		B_RendezVous_Aminah,
		B_RendezVous_Jack,

		//B_Exploring_Connectivity_Issues_Monitoring
		B_Edge_Gina,
		B_Relay_Robert,
		B_RendezVous_Mya,

		//C_Peers_And_PeerGroups
		C_200_Creating_IDS,
		C_210_Creating_A_New_Peer,
		C_220_Creating_A_Custom_PeerGroup,
		
		//D_Discovering_Resources,
		D_300_Retrieving_And_Flushing_Local_Advertisements,
		D_Edge_Maxime_The_Socializer,
		
		E_Messages_And_Advertisements,
		F_Private_Keys_X509_Certificates_And_KeyStores,
		G_Simple_Pipe_Communication,
		H_Bidirectional_Pipe_Communication,
		I_JXTA_Socket_And_Socket_Server,
		J_JXTA_Multicast_Socket,
		K_Service,
		L_Peer_Information,
		
		//Z_Tools_And_Others
		Keystore_Creation,
		JXTA_Bootstrapping,
		;
	}
	private Examples example;

	private Logger logger = Logger.getLogger( this.getClass().getName());

	protected AbstractPJ2Component( Examples example ) {
		super();
		this.example = example;
	}

	@Override
	protected void onRunJxse() {
		String[] args = Platform.getCommandLineArgs();
		try{
			switch( example ){
			case A_100_Starting_And_Stopping_JXTA:
				_100_Starting_And_Stopping_JXTA_Example.main( args );;
				break;
			case A_110_Creating_A_Local_Configuration:
				_110_Creating_A_Local_Configuration_Example.main(args);
				break;
			case A_120_Retrieving_Modifying_And_Saving_An_Existing_Configuration:
				_120_Retrieving_Modifying_And_Saving_An_Existing_Configuration_Example.main(args);
				break;
			case A_150_Configuration_Objects:
				_150_Configuration_Objects.main(args);
				break;
			case B_Edge_Anna:
				Edge_Anna.main(args);
				break;
			case B_Edge_Chihiro:
				Edge_Chihiro.main(args);
				break;
			case B_RendezVous_Aminah:
				RendezVous_Aminah.main(args);
				break;
			case B_RendezVous_Jack:
				RendezVous_Jack.main(args);
				break;
			case B_Edge_Gina:
				Edge_Gina.main( args );
				break;
			case B_Relay_Robert:
				Relay_Robert.main(args);
				break;
			case B_RendezVous_Mya:
				RendezVous_Mya.main(args);
				break;
			
				//C_Peers_And_PeerGroups:
			case C_200_Creating_IDS:
				_200_Creating_IDs_Example.main(args);
				break;
			case C_210_Creating_A_New_Peer:
				_210_Creating_A_New_Peer_Example.main(args);
				break;
			case C_220_Creating_A_Custom_PeerGroup:
				_220_Creating_A_Custom_PeerGroup_Example.main( args );
				break;
			
			//D_Discovering_Resources:
			case D_300_Retrieving_And_Flushing_Local_Advertisements:
				_300_Retrieving_And_Flushing_Local_Advertisements_Example.main(args);
				break;
			case D_Edge_Maxime_The_Socializer:
				Edge_Maxime_The_Socializer.main(args);
				break;
			case E_Messages_And_Advertisements:
				break;
			case F_Private_Keys_X509_Certificates_And_KeyStores:
				break;
			case G_Simple_Pipe_Communication:
				break;
			case H_Bidirectional_Pipe_Communication:
			case I_JXTA_Socket_And_Socket_Server:
				break;
			case J_JXTA_Multicast_Socket:
				break;
			case K_Service:
				break;
			case L_Peer_Information:
				break;
			//Z_Tools_And_Others:
			}
		}
		catch( Exception ex ){
			ex.printStackTrace();
			logger.log( Level.SEVERE, ex.getMessage(), ex);
		}
	}
}