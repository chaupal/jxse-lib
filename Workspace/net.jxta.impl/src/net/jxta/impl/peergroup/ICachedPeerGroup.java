package net.jxta.impl.peergroup;

import net.jxta.impl.cm.CacheManager;
import net.jxta.peergroup.PeerGroup;

public interface ICachedPeerGroup extends PeerGroup{

	/**
	 * @return the cache manager associated with this group.
	 */
	public abstract CacheManager getCacheManager();

}