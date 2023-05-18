import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Member {
    public int age;
    public String name;

    public Member(String[] info) {
        this.age = Integer.parseInt(info[0]);
        this.name = info[1];
    }

    static int getAge(Member member) {
        return member.age;
    }

    static String getName(Member member) {
        return member.name;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<Member> memberList = new ArrayList<>();
        String[] input;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            memberList.add(new Member(input));
        }

        memberList.sort(Comparator.comparing(Member::getAge));
        for (Member member:memberList) {
            print.append(member.age + " " + member.name).append('\n');
        }

        System.out.println(print);
    }
}