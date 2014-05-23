/*
 * Copyright (c) 2001-2007 Sun Microsystems, Inc.  All rights reserved.
 *
 *  The Sun Project JXTA(TM) Software License
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
 *     developed by Sun Microsystems, Inc. for JXTA(TM) technology." 
 *     Alternately, this acknowledgment may appear in the software itself, if 
 *     and wherever such third-party acknowledgments normally appear.
 *
 *  4. The names "Sun", "Sun Microsystems, Inc.", "JXTA" and "Project JXTA" must 
 *     not be used to endorse or promote products derived from this software 
 *     without prior written permission. For written permission, please contact 
 *     Project JXTA at http://www.jxta.org.
 *
 *  5. Products derived from this software may not be called "JXTA", nor may 
 *     "JXTA" appear in their name, without prior written permission of Sun.
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL SUN 
 *  MICROSYSTEMS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  JXTA is a registered trademark of Sun Microsystems, Inc. in the United 
 *  States and other countries.
 *
 *  Please see the license information page at :
 *  <http://www.jxta.org/project/www/license.html> for instructions on use of 
 *  the license in source files.
 *
 *  ====================================================================
 *
 *  This software consists of voluntary contributions made by many individuals 
 *  on behalf of Project JXTA. For more information on Project JXTA, please see 
 *  http://www.jxta.org.
 *
 *  This license is based on the BSD license adopted by the Apache Foundation. 
 */

package net.jxta.refplatform.peergroup;

import java.net.URI;
import net.jxta.id.ID;
import net.jxta.platform.ModuleSpecID;

/**
 * TODO: keesp: JxtaLoader removed
 * 
 * Peer groups are formed as a collection of peers that have agreed upon a
 * common set of services. Each peer group is assigned a unique peer group ID
 * and a peer group advertisement. The peer group advertisement contains a
 * ModuleSpecID which refers to a module specification for this peer group.
 * <p/>
 * The peer group specification mandates each of the group services (membership,
 * discovery, resolver, etc). Implementations of that specification are
 * described by ModuleImplAdvertisements which are identified by the group's
 * ModuleSpecID. Implementations are responsible for providing the services mandated
 * by the specification.
 * <p/>
 * The java reference implementation achieves this by loading additional Modules
 * which ModuleSpecIDs are listed by the group implementation advertisement.
 * <p/>
 * In order to fully participate in a group, a peer may need to authenticate
 * with the group using the peer group membership service.
 *
 * @see net.jxta.peergroup.PeerGroupID
 * @see net.jxta.service.Service
 * @see net.jxta.peergroup.PeerGroupFactory
 * @see net.jxta.protocol.PeerGroupAdvertisement
 * @see net.jxta.protocol.ModuleImplAdvertisement
 * @see net.jxta.platform.ModuleSpecID
 * @see net.jxta.platform.ModuleClassID
 */

public interface IModuleDefinitions{

    /**
     * Well known classes for the basic services.
     *
     * <p/>FIXME: we should make a "well-known ID" encoding implementation that
     * has its own little name space of human readable names...later.
     * To keep their string representation shorter, we put our small spec
     * or role pseudo unique ID at the front of the second UUID string.
     * Base classes do not need an explicit second UUID string because it is
     * all 0.
     *
     * <p/>The type is always the last two characters, no-matter the total length.
     */

    /**
     * Prefix string for all of the Well Known IDs declared in this interface.
     */
    static final String WK_ID_PREFIX = ID.URIEncodingName + ":" + ID.URNNamespace + ":uuid-DeadBeefDeafBabaFeedBabe";

 
    /**
     * Well known group specification identifier: the platform
     */
    public final static ModuleSpecID refPlatformSpecID =
            ModuleSpecID.create(URI.create(WK_ID_PREFIX + "000000010106"));

    /**
     * Well known group specification identifier: the Network Peer Group
     */
    public final static ModuleSpecID refNetPeerGroupSpecID =
            ModuleSpecID.create(URI.create(WK_ID_PREFIX + "000000010206"));

}
