// 00...111...2...333.44.5555.6666.777.888899

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day91 {
    public static BigInteger Fix(int[] s) {
        int l = 0;
        int r = s.length-1;

        while (l < r) {
            while (l < r && s[l] != -1) {
                l++;
            }
            while (l < r && s[r] == -1) {
                r--;
            }
    
           // System.out.println(l + " " + r + " " + s[l] + " " + s[r]);
            if (l < r) {
                s[l] = s[r];
                s[r] = -1;
            }
        }

        //BigInteger sum = BigInteger.ZERO;
        long sum = 0;
        for (int i = 0; i < s.length && s[i] != -1; i++) {
            //stem.out.println(s[i]);
            //sum = sum.add(BigInteger.valueOf(s[i]).multiply(BigInteger.valueOf(i)));
            sum += s[i] * i;
        }
        return BigInteger.valueOf(sum);
    }

    public static int [] construct(String inp) {
        int cnt  = 0;
        for (int i = 0; i < inp.length(); i++) {
            cnt += Integer.valueOf(inp.charAt(i)-'0');
        }
        int [] out = new int [cnt];
        System.err.println("inp: " + inp.length());
        int k = 0;
        for (int i = 0; i < inp.length(); i++) {
            int value =  i % 2 == 0 ? i /2 : -1;
            for (int j = 0; j < inp.charAt(i)-'0'; j++) {
                out[k++] = value;
            }
        }
        //System.out.println("out: " + out + " " + file_id);

        // int sum = 0;
        // //2  3  3  3 1 3  3 1 21  4 1 4  1 3 1 4  0 2
        // //00...111...2...333.44.5555.6666.777.888899
        // for (int i = 0, f = 0, p = 0; i < inp.length(); p += inp.charAt(i)-'0', i += 2) {
        //     // f
        //     // 3
        //     // 678
        //     // fff
        //     // 6 * f  + 7 * f + 8 * f = f(2 * a + (n-1)d)n/2) = f * ((2*a + n-1) * n)/2

        //     int n = inp.charAt(i) - '0';
        //     int a = p;
        //     sum += f * ((2 * a + n-1) * n)/2;
        //     System.out.printf("%c:%d:%d:%d\n", inp.charAt(i), p, i, sum);
        //     if (n != 0) {
        //         f++;
        //     }
        // }
        return out;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("input.txt"));
            String[] lines = input.split("\n");
            BigInteger fixed = Fix(construct(lines[0]));
            System.out.println(fixed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

