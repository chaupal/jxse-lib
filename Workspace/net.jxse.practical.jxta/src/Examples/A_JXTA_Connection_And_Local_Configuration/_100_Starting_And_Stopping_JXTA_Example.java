/*
 * Copyright (c) 2010 DawningStreams, Inc.  All rights reserved.
 *  
 *  Redistribution and use in source and binary forms, with or without 
 *  modification, are permitted provided that the following conditions are met:
 *  
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *  
 *  2. Redistributions in binary form must reproduce the above copyright notice, 
 *     this list of conditions and the following disclaimer in the documentation 
 *     and/or other materials provided with the distribution.
 *  
 *  3. The end-user documentation included with the redistribution, if any, must 
 *     include the following acknowledgment: "This product includes software 
 *     developed by DawningStreams, Inc." 
 *     Alternately, this acknowledgment may appear in the software itself, if 
 *     and wherever such third-party acknowledgments normally appear.
 *  
 *  4. The name "DawningStreams,Inc." must not be used to endorse or promote
 *     products derived from this software without prior written permission.
 *     For written permission, please contact DawningStreams,Inc. at 
 *     http://www.dawningstreams.com.
 *  
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 *  DAWNINGSTREAMS, INC OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  DawningStreams is a registered trademark of DawningStreams, Inc. in the United 
 *  States and other countries.
 *  
 */

package Examples.A_JXTA_Connection_And_Local_Configuration;

import Examples.Z_Tools_And_Others.Tools;

import java.io.File;
import java.io.IOException;

import net.jxse.osgi.compat.AbstractJP2PCompatibility;
import net.jxse.osgi.compat.IJxtaNode;
import net.jxta.exception.JxtaException;
import net.jxta.exception.PeerGroupException;
import net.jxta.peergroup.PeerGroup;
import net.jxta.platform.JxtaApplication;
import net.jxta.platform.NetworkManager;

public class _100_Starting_And_Stopping_JXTA_Example extends AbstractJP2PCompatibility<Object>{
    
	public static final String Name = "Example 100";
       
    public _100_Starting_And_Stopping_JXTA_Example() {
		super( Name );
	}

    public void main(String[] args)  {
        
        try {

            // Creation of the network manager
            File file = new File("C://Jxta/" + Name);
            if( !file.exists())
            	file.mkdirs();
        	NetworkManager MyNetworkManager = JxtaApplication.getNetworkManager(NetworkManager.ConfigMode.EDGE, Name, file.toURI() );
            MyNetworkManager.getConfigurator().setPrincipal( Name );
            MyNetworkManager.getConfigurator().setAuthenticationType(Name);
            IJxtaNode<Object> root = super.createRoot( MyNetworkManager );
            
            // Starting JXTA
            Tools.PopInformationMessage(Name, "Starting JXTA network");
            PeerGroup ConnectedVia = MyNetworkManager.startNetwork();
            root.addChild( ConnectedVia );
            
            // Diplaying peer group information
            Tools.PopInformationMessage(Name, "Connected via Peer Group: " + ConnectedVia.getPeerGroupName());
            
            // Stopping JXTA
            Tools.PopInformationMessage(Name, "Stopping JXTA network");
            MyNetworkManager.stopNetwork();
            
        } catch (IOException Ex) {
            
            // Raised when access to local file and directories caused an error
            Tools.PopErrorMessage(Name, Ex.toString());
            Ex.printStackTrace();
            
        } catch (PeerGroupException Ex) {
            
            // Raised when the net peer group could not be created
            Tools.PopErrorMessage(Name, Ex.toString());
            Ex.printStackTrace();
                       
        } catch (JxtaException e) {
			e.printStackTrace();
		}

    }

	@Override
	public void deactivate() {
		NetworkManager MyNetworkManager = (NetworkManager) super.getRoot().getModule();
		MyNetworkManager.stopNetwork();
	}    
}
