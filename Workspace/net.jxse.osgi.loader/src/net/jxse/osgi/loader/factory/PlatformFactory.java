package net.jxse.osgi.loader.factory;

import java.net.URI;

import net.jxta.document.Advertisement;
import net.jxta.exception.PeerGroupException;
import net.jxta.impl.platform.Platform;
import net.jxta.impl.protocol.PlatformConfig;
import net.jxta.module.IJxtaModuleFactory;
import net.jxta.peergroup.core.ModuleClassID;
import net.jxta.peergroup.core.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;

public class PlatformFactory implements IJxtaModuleFactory<Platform> {

	public static final String S_DESCRIPTION = "Standard World PeerGroup Reference Implementation";
	public static final String S_IDENTIFIER = "net.jxta.impl.platform.Platform";
	public static final String S_MODULE_SPEC = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010106";
	@Override
	public String getIdentifier() {
		return S_IDENTIFIER;
	}

	@Override
	public String getDescription() {
		return S_DESCRIPTION;
	}

	@Override
	public Platform createModule() {
		try {
			return new Platform();
		} catch (PeerGroupException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ModuleClassID getModuleClassID() {
		return null;
	}

	@Override
	public ModuleSpecID getModuleSpecID() {
		return ModuleSpecID.create(URI.create( S_MODULE_SPEC ));
	}

	@Override
	public ModuleImplAdvertisement getModuleImplAdvertisement() {
		return Platform.getDefaultModuleImplAdvertisement();
	}

	@Override
	public String getRepresentedClassName() {
		return S_IDENTIFIER;
	}

	@Override
	public Advertisement getAdvertisement(PlatformConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

}
