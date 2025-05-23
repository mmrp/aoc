// 00...111...2...333.44.5555.6666.777.888899

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Day92 {
    public static long  construct(String inp) {
        class Data {
            int number = 0;
            int start;
            int length;
            @Override
            public String toString() {
                return number + ":" + start + ":" + length;
            }
        };

        int n = inp.length();
        Data [] assigned = new Data [(n+1)/2];
        Data [] free = new Data [n-assigned.length];

        int start  = 0;
        for (int i = 0; i < n; i++) {
            Data d = new Data();
            d.start = start;
            d.length = Integer.valueOf(inp.charAt(i) - '0') ;
            d.number = i/2;
            start += d.length;
            if (i %2 == 0) {
                assigned[i/2] = d;
            } else {
                free[i/2] = d;
            }
        }

        List<Data> new_assigned = new ArrayList<>();
        for (int j = assigned.length-1; j >= 0; j--) {
            for (int i = 0; i < free.length; i++) {
                if (free[i].start < assigned[j].start && assigned[j].length <= free[i].length) {
                    Data d = new Data();
                    d.start = free[i].start;
                    d.length = assigned[j].length;
                    d.number = assigned[j].number;
                    new_assigned.add(d);

                    free[i].length -= d.length;
                    free[i].start += d.length;
                    assigned[j].start = -1;
                    break;
                }
            }
        }
        
        for (Data d: assigned) {
            new_assigned.add(d);
        }

        Collections.sort(new_assigned, new Comparator<Data>() {
            public int compare(Data d1, Data d2) {
                return d1.start - d2.start;
            }
        });

        int cnt = 0;
        for (Data d: new_assigned) {
            if (d.start < 0) continue;
            if (cnt < d.start + d.length) {
                cnt = d.start + d.length;
            }
        }

        for (Data d: free) {
            cnt += d.length;
        }

        long ans = 0;
        for (Data d: new_assigned) {
            if (d.start < 0) continue;
            for (int i = 0; i < d.length; i++) {
                ans += (i+d.start) * d.number;
            }
        }

        System.out.println();
        return ans;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("input.txt"));
            String[] lines = input.split("\n");
            System.out.println(construct(lines[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

