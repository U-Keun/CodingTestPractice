import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N;
    static PriorityQueue<Lecture> lectures = new PriorityQueue<>();
    static PriorityQueue<Record> endTimeRecord = new PriorityQueue<>();
    static int[] lectureRoom;
    public static void main(String[] args) throws IOException {
        setVariables();
        while (!lectures.isEmpty()) {
            Lecture tmp = lectures.poll();
            if (endTimeRecord.isEmpty()) {
                endTimeRecord.add(new Record(tmp.endTime, 1));
                lectureRoom[tmp.id - 1] = 1;
                continue;
            }
            if (endTimeRecord.peek().endTime <= tmp.startTime) {
                Record update = new Record(tmp.endTime, endTimeRecord.poll().roomNumber);
                lectureRoom[tmp.id - 1] = update.roomNumber;
                endTimeRecord.add(update);
            } else {
                endTimeRecord.add(new Record(tmp.endTime, endTimeRecord.size() + 1));
                lectureRoom[tmp.id - 1] = endTimeRecord.size();
            }
        }
        print.append(endTimeRecord.size()).append('\n');
        for (Integer i:lectureRoom) {
            print.append(i).append('\n');
        }
        System.out.println(print);
    }
    static void setVariables() throws IOException {
        N = Integer.parseInt(br.readLine());
        int id, startTime, endTime;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            id = Integer.parseInt(st.nextToken());
            startTime = Integer.parseInt(st.nextToken());
            endTime = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(id, startTime, endTime));
        }
        lectureRoom = new int[N];
    }
}
class Lecture implements Comparable<Lecture> {
    int id;
    int startTime;
    int endTime;
    public Lecture(int id, int startTime, int endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override // 강의 시작 시간이 빠른 것부터 나열하도록 우선순위 설정
    public int compareTo(Lecture otherLecture) {
        if (this.startTime == otherLecture.startTime) {
            if (this.endTime == otherLecture.endTime) {
                return this.id - otherLecture.id;
            } else return this.endTime - otherLecture.endTime;
        } else return this.startTime - otherLecture.startTime;
    }
}
class Record implements Comparable<Record> {
    int endTime;
    int roomNumber;
    public Record(int endTime, int roomNumber) {
        this.endTime = endTime;
        this.roomNumber = roomNumber;
    }
    @Override
    public int compareTo(Record otherRecord) {
        if (this.endTime == otherRecord.endTime) {
            return this.roomNumber - otherRecord.roomNumber;
        } else return this.endTime - otherRecord.endTime;
    }
}