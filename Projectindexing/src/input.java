/***************************************************************************************************************************
 * Written by Jossan,Gagandeep Singh | NetID: gxj170003
 * STARTED ON DATE 2018-11-04
 * CS6360.004 Assignment 5
 * PURPOSE : For reading the KEY and offset from an input file 'CS6360Asg5TestDataA.txt'  and returns to main
 * { when scanned operation is  '-c' }
 ***************************************************************************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class input {
    // returns key and offset by reading it from input file

    public List<List<String>> returninputfile(String I, int n) throws IOException
    {
        // masterlst stores key and offset
        // read every line from input file and stor key and offset individually in masterlst[0][]
        List<List<String>> masterlst = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(I, "r")){
            String line;
            int j=0;
            long temp=0;
            while ( (line = raf.readLine()) != null )
            {
                String i = Long.toString(temp);
                String sb =line.substring(0,n);
                masterlst.add(new ArrayList<>());
                masterlst.get(j).add(sb);
                masterlst.get(j).add(i);
                j++;
                temp=temp+line.length()+2;
            }
            //System.out.print(masterlst);

        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        return masterlst;
    }
}

