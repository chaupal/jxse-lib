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
import Examples.B_Exploring_Connectivity_Issues.Monitoring.Edge_Gina;
import Examples.B_Exploring_Connectivity_Issues.Monitoring.Relay_Robert;
import Examples.B_Exploring_Connectivity_Issues.Monitoring.RendezVous_Mya;
import Examples.C_Peers_And_PeerGroups._200_Creating_IDs_Example;
import Examples.C_Peers_And_PeerGroups._210_Creating_A_New_Peer_Example;
import Examples.C_Peers_And_PeerGroups._220_Creating_A_Custom_PeerGroup_Example;
import Examples.D_Discovering_Resources.Edge_Maxime_The_Socializer;
import Examples.D_Discovering_Resources._300_Retrieving_And_Flushing_Local_Advertisements_Example;
import Examples.E_Messages_And_Advertisements._400_Creating_An_Empty_Message_Example;
import Examples.E_Messages_And_Advertisements._410_Add_String_Long_Int_Elements_Example;
import Examples.E_Messages_And_Advertisements._420_Retrieving_Message_Elements_Example;
import Examples.E_Messages_And_Advertisements._430_Add_ByteArray_Element_And_Retrieve_InputStream_Example;
import Examples.E_Messages_And_Advertisements._440_Adding_An_Advertisement_In_Message_Example;
import Examples.E_Messages_And_Advertisements._510_Registering_Customized_Advertisement_Instance_Example;
import Examples.F_Private_Keys_X509_Certificates_And_KeyStores._600_Exporting_And_Importing_Private_Keys_And_X509_Certificates;
import Examples.F_Private_Keys_X509_Certificates_And_KeyStores._610_Working_With_A_Keystore;
import Examples.F_Private_Keys_X509_Certificates_And_KeyStores._620_Creating_A_Custom_PSE_PeerGroup_Example;
import Examples.G_Simple_Pipe_Communication.Edge_Dimitri_Sending_Messages;
import Examples.G_Simple_Pipe_Communication.RendezVous_Chandra_Receiving_Messages;
import Examples.H_Bidirectional_Pipe_Communication.Edge_Quinisela_At_The_Other_End;
import Examples.H_Bidirectional_Pipe_Communication.RendezVous_Adelaide_At_One_End;
import Examples.I_JXTA_Socket_And_Socket_Server.Edge_Ayrton_The_JXTA_Socket;
import Examples.I_JXTA_Socket_And_Socket_Server.RendezVous_Lidong_The_JXTA_Socket_Server;
import Examples.J_JXTA_Multicast_Socket.Edge_Teyacapan_Another_Multicast_Participant;
import Examples.J_JXTA_Multicast_Socket.RendezVous_Hans_A_Multicast_Participant;
import Examples.K_Service.Edge_Jill_The_Customer;
import Examples.K_Service.RendezVous_Joe_The_Astrologer;
import Examples.K_Service._700_Module_IDs_Creation_Example;
import Examples.K_Service._710_Astrology_Service_Example;
import Examples.L_Peer_Information._800_Adhoc_Ping_Example;
import Examples.L_Peer_Information._810_Adhoc_Pong_Example;
import Examples.Z_Tools_And_Others.JXTA_Bootstrapping;
import Examples.Z_Tools_And_Others.Keystore_Creation_Example;
import net.osgi.jxse.AbstractJxseComponent;
import net.osgi.jxse.IJP2PCompatibility;

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
		
		//Messages_And_Advertisements,
		E_400_Creating_An_Empty_Message,
		E_410_Add_String_Long_Int_Elements,
		E_420_Retrieving_Message_Elements,
		E_430_Add_ByteArray_Element_And_Retrieve_InputStream,
		E_440_Adding_An_Advertisement_In_Message,
		E_500_Customized_Advertisement,
		E_510_Registering_Customized_Advertisement_Instance,
		
		//Private_Keys_X509_Certificates_And_KeyStores,
		F_600_Exporting_And_Importing_Private_Keys_And_X509_Certificates,
		F_610_Working_With_A_Keystore,
		F_620_Creating_A_Custom_PSE_PeerGroup,
		
		//Simple_Pipe_Communication,
		G_Edge_Dimitri_Sending_Messages,
		G_RendezVous_Chandra_Receiving_Messages,
		
		//Bidirectional_Pipe_Communication,
		H_Edge_Quinisela_At_The_Other_End,
		H_RendezVous_Adelaide_At_One_End,
		
		//JXTA_Socket_And_Socket_Server
		I_Edge_Ayrton_The_JXTA_Socket,
		I_RendezVous_Lidong_The_JXTA_Socket_Server,
		
		//J_JXTA_Multicast_Socket
		J_Edge_Teyacapan_Another_Multicast_Participant,
		J_RendezVous_Hans_A_Multicast_Participant,
		
		//K_Service
		K_700_Module_IDs_Creation,
		K_710_Astrology_Service,
		K_Edge_Jill_The_Customer,
		K_RendezVous_Joe_The_Astrologer,
		
		//Peer_Information
		L_800_Adhoc_Ping,
		L_810_Adhoc_Pong,
		
		//Z_Tools_And_Others
		Keystore_Creation,
		JXTA_Bootstrapping;
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
		IJP2PCompatibility<Object> compat = null;
		try{
			switch( example ){
			case A_100_Starting_And_Stopping_JXTA:
				compat = new _100_Starting_And_Stopping_JXTA_Example();
				break;
			case A_110_Creating_A_Local_Configuration:
				compat = new _110_Creating_A_Local_Configuration_Example();
				break;
			case A_120_Retrieving_Modifying_And_Saving_An_Existing_Configuration:
				compat =new _120_Retrieving_Modifying_And_Saving_An_Existing_Configuration_Example();
				break;
			case A_150_Configuration_Objects:
				compat = new _150_Configuration_Objects();
				break;
			
			//B_Exploring_Connectivity_Issues
			case B_Edge_Anna:
				compat = new Edge_Anna();
				break;
			case B_Edge_Chihiro:
				compat = new Edge_Chihiro();
				break;
			case B_RendezVous_Aminah:
				compat = new  RendezVous_Aminah();
				break;
			case B_RendezVous_Jack:
				compat = new RendezVous_Jack();
				break;
			
			//B_Exploring_Connectivity_Issues.Monitoring
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
			
			//Messages_And_Advertisements
			case E_400_Creating_An_Empty_Message:
				_400_Creating_An_Empty_Message_Example.main( args );
				break;
			case E_410_Add_String_Long_Int_Elements:
				_410_Add_String_Long_Int_Elements_Example.main(args);
				break;
			case E_420_Retrieving_Message_Elements:
				_420_Retrieving_Message_Elements_Example.main(args);
				break;
			case E_430_Add_ByteArray_Element_And_Retrieve_InputStream:
				_430_Add_ByteArray_Element_And_Retrieve_InputStream_Example.main(args);
				break;
			case E_440_Adding_An_Advertisement_In_Message:
				_440_Adding_An_Advertisement_In_Message_Example.main(args);
				break;
			case E_510_Registering_Customized_Advertisement_Instance:
				_510_Registering_Customized_Advertisement_Instance_Example.main(args);
				break;
			
			//F Private_Keys_X509_Certificates_And_KeyStores:
			case F_600_Exporting_And_Importing_Private_Keys_And_X509_Certificates:
				_600_Exporting_And_Importing_Private_Keys_And_X509_Certificates.main(args);
				break;
			case F_610_Working_With_A_Keystore:
				_610_Working_With_A_Keystore.main(args);
				break;
			case F_620_Creating_A_Custom_PSE_PeerGroup:
				_620_Creating_A_Custom_PSE_PeerGroup_Example.main(args);
				break;
			
			//Simple_Pipe_Communication:
			case G_Edge_Dimitri_Sending_Messages:
				Edge_Dimitri_Sending_Messages.main(args);
				break;
			case G_RendezVous_Chandra_Receiving_Messages:
				RendezVous_Chandra_Receiving_Messages.main(args);
				break;
				
			//Bidirectional_Pipe_Communication,
			case H_Edge_Quinisela_At_The_Other_End:
				Edge_Quinisela_At_The_Other_End.main(args);
				break;
			case H_RendezVous_Adelaide_At_One_End:
				RendezVous_Adelaide_At_One_End.main(args);
				break;
				
			//JXTA_Socket_And_Socket_Server
			case I_Edge_Ayrton_The_JXTA_Socket:
				Edge_Ayrton_The_JXTA_Socket.main(args);
				break;
			case I_RendezVous_Lidong_The_JXTA_Socket_Server:
				RendezVous_Lidong_The_JXTA_Socket_Server.main(args);
				break;
				
			//J_JXTA_Multicast_Socket
			case J_Edge_Teyacapan_Another_Multicast_Participant:
				Edge_Teyacapan_Another_Multicast_Participant.main(args);
				break;
			case J_RendezVous_Hans_A_Multicast_Participant:
				RendezVous_Hans_A_Multicast_Participant.main(args);
				break;
				
			//K_Service
			case K_700_Module_IDs_Creation:
				_700_Module_IDs_Creation_Example.main(args);
				break;
			case K_710_Astrology_Service:
				_710_Astrology_Service_Example.main(args);
				break;
			case K_Edge_Jill_The_Customer:
				Edge_Jill_The_Customer.main(args);
				break;
			case K_RendezVous_Joe_The_Astrologer:
				RendezVous_Joe_The_Astrologer.main(args);
				break;
				
			//Peer_Information
			case L_800_Adhoc_Ping:
				_800_Adhoc_Ping_Example.main(args);
				break;
			case L_810_Adhoc_Pong:
				_810_Adhoc_Pong_Example.main(args);
				break;
				
			//Z_Tools_And_Others
			case Keystore_Creation:
				try {
					Keystore_Creation_Example.main(args);
				} catch (Throwable e) {
					logger.severe( e.getMessage());
					e.printStackTrace();
				}
				break;
			case JXTA_Bootstrapping:
				try {
					JXTA_Bootstrapping.main( args );
				} catch (Throwable e) {
					logger.severe( e.getMessage());
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
			
			if( compat != null )
				compat.main( args );;
		}
		catch( Exception ex ){
			ex.printStackTrace();
			logger.log( Level.SEVERE, ex.getMessage(), ex);
		}
	}
}