import java.io.*;
import java.util.*;



    public class Match {

        public ArrayList<Student> readFile() {

            System.out.println("Enter file name: ");
            Scanner input = new Scanner(System.in);
            String roster = input.next();

            return readFile(roster);

        }

        public ArrayList<Student> readFile(String fileName) {
            ArrayList<Student> students = new ArrayList<Student>();

            try {

                Scanner input = new Scanner(new FileReader(fileName));

                while (input.hasNextLine()) {

                    @SuppressWarnings("resource")
                    Scanner s = new Scanner(input.nextLine());

                    s.useDelimiter("[\t-]");

                    String name = s.next();

                    char c = s.next().charAt(0);

                    Date d = new Date(s.nextInt(), s.nextInt(), s.nextInt());

                    Preference p = new Preference(s.nextInt(), s.nextInt(),
                            s.nextInt(), s.nextInt());

                    Student s1 = new Student(name, c, d, p);

                    students.add(s1);

                    // System.out.println(students.get(0).toString(s1));
                    //
                    // System.out.println(count);
                }

                input.close();

            } catch (NoSuchElementException e) {

                System.out.println(e);
            } catch (FileNotFoundException e) {

                System.out.println(e);
            }

            return students;
        }

        public static void finalMatches(Match m) {

            ArrayList<Student> students = m.readFile();
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getMatched()) {
                    continue;
                }
                int maxScore = 0;
                Student bestMatchStudent = null;

                for (int j = i + 1; j < students.size(); j++) {
                    if (students.get(j).getMatched()) {
                        continue;
                    }

                    int currentScore = students.get(i).compare(students.get(j));

                    if (currentScore > maxScore) {
                        maxScore = currentScore;
                        bestMatchStudent = students.get(j);
                    }
                }
                if (bestMatchStudent != null) {
                    students.get(i).setMatched(true);
                    bestMatchStudent.setMatched(true);
                    System.out.println(students.get(i).getName() + " matches with "
                            + bestMatchStudent.getName() + " with score "
                            + maxScore);

                } else {
                    System.out.println(students.get(i).getName()
                            + " has no matches.");
                }
            }
        }

        public static void main(String[] args) {

            Match m = new Match();

            finalMatches(m);

        }

    }
