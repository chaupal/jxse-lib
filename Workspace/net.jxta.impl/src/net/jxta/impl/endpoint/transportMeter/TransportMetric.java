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

package net.jxta.impl.endpoint.transportMeter;

import net.jxta.document.Element;
import net.jxta.document.TextElement;
import net.jxta.endpoint.EndpointAddress;
import net.jxta.util.documentSerializable.DocumentSerializable;
import net.jxta.util.documentSerializable.DocumentSerializableUtilities;
import net.jxta.util.documentSerializable.DocumentSerializationException;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The Metric for a single Transport
 **/
public class TransportMetric implements DocumentSerializable {
    private String protocol;
    private EndpointAddress endpointAddress;
    private HashMap<EndpointAddress, TransportBindingMetric> transportBindingMetrics = new HashMap<EndpointAddress, TransportBindingMetric>();

    public TransportMetric(TransportMeter transportMeter) {
        this.endpointAddress = transportMeter.getEndpointAddress();
        this.protocol = transportMeter.getProtocol();
    }

    public TransportMetric() {}

    public TransportMetric(TransportMetric prototype) {
        this.endpointAddress = prototype.endpointAddress;
        this.protocol = prototype.protocol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TransportMetric) {
            TransportMetric other = (TransportMetric) obj;

            return protocol.equals(other.protocol) && endpointAddress.equals(other.endpointAddress);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { 
        return endpointAddress.hashCode();
    }

    public EndpointAddress getEndpointAddress() {
        return endpointAddress;
    }

    public String getProtocol() {
        return protocol;
    }
	
    public synchronized void addTransportBindingMetric(TransportBindingMetric transportBindingMetric) {
        transportBindingMetrics.put(transportBindingMetric.getEndpointAddress(), transportBindingMetric);
    }

    public TransportBindingMetric getTransportBindingMetric(EndpointAddress endpointAddress) {
        return transportBindingMetrics.get(endpointAddress);
    }

    public TransportBindingMetric getTransportBindingMetric(TransportBindingMetric prototype) { 
        return getTransportBindingMetric(prototype.getEndpointAddress());
    }

    public Iterator<TransportBindingMetric> getTransportBindingMetrics() {
        return transportBindingMetrics.values().iterator();
    }

    public int getTransportBindingMetricsCount() {
        return transportBindingMetrics.size();
    }
	
    public void serializeTo(Element element) throws DocumentSerializationException {
        DocumentSerializableUtilities.addString(element, "endpointAddress", endpointAddress.toString());
        DocumentSerializableUtilities.addString(element, "protocol", protocol);

        for (Iterator<TransportBindingMetric> i = getTransportBindingMetrics(); i.hasNext();) {
            TransportBindingMetric transportBindingMetric = i.next();

            DocumentSerializableUtilities.addDocumentSerializable(element, "binding", transportBindingMetric);		
        }
    }

    public void initializeFrom(Element element) throws DocumentSerializationException {
        for (Enumeration e = element.getChildren(); e.hasMoreElements();) {
            Element childElement = (TextElement) e.nextElement();
            String tagName = (String) childElement.getKey();
			
            if (tagName.equals("endpointAddress")) {
                String endpointAddressString = DocumentSerializableUtilities.getString(childElement);	

                endpointAddress = new EndpointAddress(endpointAddressString);
            } else if (tagName.equals("protocol")) {
                protocol = DocumentSerializableUtilities.getString(childElement);
            } else if (tagName.equals("binding")) {
                TransportBindingMetric transportBindingMetric = (TransportBindingMetric) DocumentSerializableUtilities.getDocumentSerializable(
                        childElement, TransportBindingMetric.class);

                transportBindingMetrics.put(transportBindingMetric.getEndpointAddress(), transportBindingMetric);
            }
        }
    }

    void mergeMetrics(TransportMetric otherTransportMetric) {
        for (Iterator<TransportBindingMetric> i = otherTransportMetric.getTransportBindingMetrics(); i.hasNext();) {
            TransportBindingMetric otherTransportBindingMetric = i.next();
            TransportBindingMetric transportBindingMetric = getTransportBindingMetric(
                    otherTransportBindingMetric.getEndpointAddress());

            if (transportBindingMetric == null) {
                transportBindingMetric = new TransportBindingMetric(otherTransportBindingMetric);
                addTransportBindingMetric(transportBindingMetric);
            }
			 
            transportBindingMetric.mergeMetrics(otherTransportBindingMetric);			
        }
				
    }
}
