/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xindice" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 1999-2001, The dbXML
 * Group, L.L.C., http://www.dbxmlgroup.com.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package net.jxta.impl.xindice.core;

import net.jxta.impl.xindice.util.XindiceException;

/**
 * A DBException is thrown by the database if an exception occurs in the
 * managing (creating, dropping) database objects such as Collections,
 * Indexes, and XMLObjects.
 */
public class DBException extends XindiceException {
	private static final long serialVersionUID = 1L;
	public FaultCodes fault;

    public DBException() {
        this(FaultCodes.GEN_UNKNOWN, null, null);
    }

    public DBException(int faultCode) {
        this(faultCode, null, null);
    }

    public DBException(int faultCode, String message) {
        this(faultCode, message, null);
    }

    public DBException(int faultCode, String message, Throwable cause) {
        this(FaultCodes.toFaultCodes(faultCode), message, cause);
    }

    public DBException(FaultCodes fault, String message) {
        this(fault, message, null);
    }

    public DBException(FaultCodes fault, Throwable cause) {
        this(fault, null, cause);
    }

    public DBException(FaultCodes fault, String message, Throwable cause) {
        super(message, cause);

        this.fault = fault;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        String faultMessage = fault.getMessage();
        String message = super.getMessage();
        if(null != message) {
            StringBuilder composedMessage = new StringBuilder(faultMessage.length() + 3 + message.length());
            composedMessage.append(faultMessage).append(" : ").append(message);

            return composedMessage.toString();
        } else {
            return faultMessage;
        }
    }
}
