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

package net.jxta.protocol;

import net.jxta.document.Document;
import net.jxta.document.Element;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredDocument;
import net.jxta.document.StructuredDocumentUtils;
import net.jxta.peer.PeerID;

/**
 * This abstract class define the PeerInfoService Query message.
 *
 **/
public abstract class PeerInfoQueryMessage {

    /**
     * The Sender's (or Querying) Peer Id.
     */
    private PeerID spid = null;

    /**
     * Peer Id of the peer which published this advertisement.
     */
    private PeerID tpid = null;

    /* representing request  */
    private StructuredDocument<?> request = null;

    public PeerInfoQueryMessage() {
        super();
    }

    /**
     * returns the Message type
     * @return a string
     *
     * @since JXTA 1.0
     */
    public static String getMessageType() {
        return "jxta:PeerInfoQueryMessage";
    }

    /**
     * returns the sender's pid
     * @return a string representing the peer's id
     *
     * @since JXTA 1.0
     */
    public PeerID getSourcePid() {
        return spid;
    }

    /**
     * sets the sender's pid
     * @param pid a string representing a peer's id
     *
     * @since JXTA 1.0
     */
    public void setSourcePid(PeerID pid) {
        this.spid = pid;
    }

    /**
     * returns the target pid
     * @return a string representing the peer's id
     *
     * @since JXTA 1.0
     */
    public PeerID getTargetPid() {
        return tpid;
    }

    /**
     * sets the target's pid
     * @param pid a string representing a peer's id
     *
     * @since JXTA 1.0
     */
    public void setTargetPid(PeerID pid) {
        this.tpid = pid;
    }

    /**
     * returns the request
     * @return a structured document representing request
     *
     * @since JXTA 1.0
     */
    public Element<?> getRequest() {
        if (null != request) {
            return StructuredDocumentUtils.copyAsDocument(request);
        } else {
            return null;
        }
    }

    /**
     * sets the request
     * @param request a structured document representing a peerinfo request
     *
     * @since JXTA 1.0
     */
    public void setRequest(Element<?> request) {
        if (null != request) {
            this.request = StructuredDocumentUtils.copyAsDocument(request);
        } else {
            this.request = null;
        }
    }

    public abstract Document getDocument(MimeMediaType encodeAs);
}

