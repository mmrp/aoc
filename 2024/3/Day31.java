import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;

class Day31 {
    public static void main(String[] args) {
        String fileName = "input.txt"; // Replace with your file name
        String content = "";
        try {
            content = Files.readString(Paths.get(fileName));
            System.out.println(content); // The whole file as a single string
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(content);

        int ans = 0;
        while (matcher.find()) {
            System.out.println(matcher.group());
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            ans += num1 * num2;
            System.out.println("num1: " + num1 + ", num2: " + num2);
        }
        System.out.println("ans: " + ans);
    }
}

