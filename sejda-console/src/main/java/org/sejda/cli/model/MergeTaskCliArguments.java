/*
 * Created on Sep 3, 2011
 * Copyright 2010 by Eduard Weissmann (edi.weissmann@gmail.com).
 * 
 * This file is part of the Sejda source code
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sejda.cli.model;

import java.util.List;

import org.sejda.conversion.MultiplePageRangeSetAdapter;
import org.sejda.conversion.OutlinePolicyAdapter;
import org.sejda.conversion.PdfFileSourceAdapter;
import org.sejda.conversion.PdfFileSourceListAdapter;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;

/**
 * Specifications for command line options of the Concat/Merge task
 * 
 * @author Eduard Weissmann
 * 
 */
@CommandLineInterface(application = TaskCliArguments.EXECUTABLE_NAME + " merge")
public interface MergeTaskCliArguments extends CliArgumentsWithPdfFileOutput {

    @Option(description = "input pdf documents contain forms (high memory usage) (optional)")
    boolean isCopyFields();

    @Option(description = "add a blank page after each merged document if the number of pages is odd (optional)")
    boolean isAddBlanks();

    @Option(shortName = "d", description = "directory containing pdf files to merge. Files will be merged in alphabetical order. (optional)")
    PdfFileSourceListAdapter getDirectory();

    boolean isDirectory();

    @Option(shortName = "e", description = "regular expression the file names have to match when the directory input is used (Ex -e \"test(.*).pdf\"). (optional)")
    String getMatchingRegEx();

    boolean isMatchingRegEx();

    // TODO: make sure optional options are reflected in the jewelcli produced help output, add isXXX
    @Option(shortName = "s", description = "page selection script. You can set a subset of pages to merge as a colon separated list of page selections. Order of the pages is relevant. Accepted values: 'all' or 'num1-num2' or"
            + " 'num-' or 'num1,num2-num3..' (EX. -f /tmp/file1.pdf /tmp/file2.pdf -s all:all:), "
            + "(EX. -f /tmp/file1.pdf /tmp/file2.pdf /tmp/file3.pdf -s all:12-14:32,12-14,4,34-:) to merge file1.pdf, pages 12,13,14 of file2.pdf and pages 32,12,13,14,4,34,35.. of file3.pdf. "
            + "If -s is not set default behaviour is to merge document completely (optional)", defaultValue = "")
    MultiplePageRangeSetAdapter getPageSelection();

    @Override
    @Option(shortName = "f", description = FILES_OPTION_DESCRIPTION_WHEN_EXPECTING_A_LIST_OPTIONAL)
    List<PdfFileSourceAdapter> getFiles();

    boolean isFiles();

    @Option(shortName = "l", description = "xml or csv file containing pdf files list to concat. If csv file in comma separated value format; "
            + "if xml file <filelist><file value=\"filepath\" /></filelist> (optional)")
    PdfFileSourceListAdapter getFilesListConfig();

    boolean isFilesListConfig();

    @Option(shortName = "b", description = "bookmarks merge policy. {discard, retain, one_entry_each_doc }. Default is 'retain' (optional)", defaultValue = "retain")
    OutlinePolicyAdapter getBookmarks();

}
