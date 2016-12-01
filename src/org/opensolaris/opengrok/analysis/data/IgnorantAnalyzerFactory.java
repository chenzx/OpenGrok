/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * See LICENSE.txt included in this distribution for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright (c) 2007, 2015, Oracle and/or its affiliates. All rights reserved.
 */

package org.opensolaris.opengrok.analysis.data;

import org.opensolaris.opengrok.analysis.FileAnalyzer;
import org.opensolaris.opengrok.analysis.FileAnalyzerFactory;

/**
 * Factory for analyzer that claims to analyze files which are mostly plain
 * text data, but ignores them.
 */
public class IgnorantAnalyzerFactory extends FileAnalyzerFactory {
    private static final String[] SUFFIXES = {
        "BDF", "XPM", "PS", "AFM", "PDF", "LIB", "PDB"
        ,"O","EXE","DLL","LIB"
        ,"SO","A","NEXE"
        ,"WEBM","WEBP","MP4","WMV","MP3","PNG","JPG","BMP","SVG","JPEG","TIFF","UCM","ICO"
        ,"GIF","WAV","VP9"
        ,"TGZ","TAR","BZ2","JAR","ZIP","Z","7Z","RAR"
        ,"JSON","XML","TXT","HTML"
        ,"PYC"
        ,"PAK","GRD","XTB","STAMP"
    };

    private static final String[] MAGICS = {
        "%!PS-",                // post script files
        "# PaCkAg",
        "%PDF",
        "Microsoft C/C++ MSF ", // PDB files: https://msdn.microsoft.com/en-us/library/yd4f8bd1(vs.71).aspx
        "!<arch>", // LIB files: https://msdn.microsoft.com/en-us/library/ba1z7822.aspx
        
    };

    public IgnorantAnalyzerFactory() {
        super(null, null, SUFFIXES, MAGICS, null, null, null, null);
    }

    @Override
    protected FileAnalyzer newAnalyzer() {
        // just use a FileAnalyzer since it won't analyze or xref the file
        return new FileAnalyzer(this);
    }
}
