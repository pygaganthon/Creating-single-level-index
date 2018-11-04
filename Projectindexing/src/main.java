/******************************************************************************************************************
 * Written by Jossan,Gagandeep Singh | NetID: gxj170003
 * STARTED ON DATE 2018-11-04
 * CS6360.004 Assignment 5
 * PURPOSE : scans an operation '-c' or '-l' and performs certain operations
 * if '-c' then it scans the KEY value and calculates offest for each key and then sorts the key and then writes the sorted
 * key along with offset in an output file or index file
 * if '-l' then it uses the offset of every key from index file(output file in first part) to map to the particular position of key in input file (found at at that offset)
 * and then displays the full line including the key. In this way it sorts the lines of input file in ascending order as per the key
 *******************************************************************************************************************/

import java.io.*;
import java.io.BufferedReader;
import java.util.*;

public class main {

    public static void main(String[] args) throws IOException {

        BufferedReader bs = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer St = new StringTokenizer(bs.readLine());
        String cl = St.nextToken(); // scans '-c' or '-l' operation
        String I = St.nextToken(); // input file name in directory
        String O = St.nextToken(); // output file name
        int n = Integer.parseInt(St.nextToken()); // length to be indexed (key)


        if (cl.equals("-c"))
        {

            // scan the file by calling to returninputfile
            input obj = new input();
            List<List<String>> fileinput = obj.returninputfile(I, n);

            //sorts the read data (including offset and n length key) based on key
            Collections.sort(fileinput, new Comparator<List<String>>() {
                @Override
                public int compare(List<String> o1, List<String> o2) {
                    return o1.get(0).compareTo(o2.get(0));
                }
            });

            //prints sorted key and offset values
            //System.out.print(fileinput);

            // writes the sorted key and offset values to output file with output file name O
            write obj2 = new write();
            obj2.writingToFile(fileinput, O);
       }


        // O is the index file
        // I is th input file

        if(cl.equals("-l"))
        {
            // creates a random access file stream to read from
            // skips the key and seeks the position to find offset of that particular in input file
            // prints the whole line when offset is found
            // in this way it prints each record of input file in sorted manner
            try (RandomAccessFile rf = new RandomAccessFile(O, "r"))
            {
                rf.skipBytes(2*n);
                Long line;
                RandomAccessFile rif = new RandomAccessFile(I, "r");
                List<String> lstindex = new ArrayList<>();
                rif.seek(0);
                while ( (line=rf.readLong()) != null ) {

                    rif.seek(line);
                    System.out.println(rif.readLine());
                    rf.skipBytes(2 * n);
                    rif.seek(0);
                }
            }
            catch(EOFException e) {}
            catch (FileNotFoundException fnfe) {
            } catch (IOException ioe) {
                System.err.println(ioe);
            }

        }


    }
}