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
// (C) 2013 Red Hat, Inc.
// All rights reserved.
// --- END COPYRIGHT BLOCK ---

package com.netscape.cmstools.tps.cert;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;

import com.netscape.certsrv.tps.cert.TPSCertData;
import com.netscape.cmstools.cli.CLI;
import com.netscape.cmstools.cli.MainCLI;

/**
 * @author Endi S. Dewata
 */
public class TPSCertShowCLI extends CLI {

    public TPSCertCLI certCLI;

    public TPSCertShowCLI(TPSCertCLI certCLI) {
        super("show", "Show certificate", certCLI);
        this.certCLI = certCLI;
    }

    public void printHelp() {
        formatter.printHelp(getFullName() + " <Certificate ID> [OPTIONS...]", options);
    }

    public void execute(String[] args) throws Exception {
        // Always check for "--help" prior to parsing
        if (Arrays.asList(args).contains("--help")) {
            // Display usage
            printHelp();
            System.exit(0);
        }

        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            printHelp();
            System.exit(-1);
        }

        String[] cmdArgs = cmd.getArgs();

        if (cmdArgs.length != 1) {
            System.err.println("Error: No Certificate ID specified.");
            printHelp();
            System.exit(-1);
        }

        String certID = args[0];

        TPSCertData certData = certCLI.certClient.getCert(certID);

        MainCLI.printMessage("Certificate \"" + certID + "\"");

        TPSCertCLI.printCert(certData);
    }
}
