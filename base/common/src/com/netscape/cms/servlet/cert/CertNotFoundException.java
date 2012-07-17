// --- BEGIN COPYRIGHT BLOCK ---
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; version 2 of the License.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along
// with this program; if not, write to the Free Software Foundation, Inc.,
// 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
//
// (C) 2007 Red Hat, Inc.
// All rights reserved.
// --- END COPYRIGHT BLOCK ---
package com.netscape.cms.servlet.cert;

import javax.ws.rs.core.Response;

import com.netscape.certsrv.dbs.certdb.CertId;
import com.netscape.cms.servlet.base.CMSException;

public class CertNotFoundException extends CMSException {

    private static final long serialVersionUID = -4784839378360933483L;

    public CertId certId;

    public CertNotFoundException(CertId certId) {
        this(certId, "Certificate ID " + certId.toHexString() + " not found");
    }

    public CertNotFoundException(CertId certId, String message) {
        super(Response.Status.NOT_FOUND, message);
        this.certId = certId;
    }

    public CertNotFoundException(CertId certId, String message, Throwable cause) {
        super(Response.Status.NOT_FOUND, message, cause);
        this.certId = certId;
    }

    public CertNotFoundException(Data data) {
        super(data);
        certId = new CertId(data.getAttribute("certId"));
    }

    public Data getData() {
        Data data = super.getData();
        data.setAttribute("certId", certId.toString());
        return data;
    }

    public CertId getCertId() {
        return certId;
    }

    public void setRequestId(CertId certId) {
        this.certId = certId;
    }
}