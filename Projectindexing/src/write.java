/******************************************************************************************************************
 * Written by Jossan,Gagandeep Singh | NetID: gxj170003
 * STARTED ON DATE 2018-11-04
 * CS6360.004 Assignment 5
 * PURPOSE : For writing 'KEY' and 'Offset' to output file   { when scanned '-c' }
 *******************************************************************************************************************/

import java.io.*;
import java.util.*;

public class write
{
    // writes offset as 8 bytes
    // writes every char of key in byte value
    public static void writingToFile(List<List<String>> in, String O) throws IOException
    {
        File fil = new File(O);
        fil.setWritable(true);
        RandomAccessFile file = new RandomAccessFile(O, "rw");
        int linenumber = 0;
        while(linenumber<in.size())
        {
            file.writeChars(in.get(linenumber).get(0));
            file.writeLong(Long.parseLong(in.get(linenumber).get(1)));
            linenumber++;
        }
        file.close();
        fil.setWritable(false);
        fil.setReadOnly();
    }
}

