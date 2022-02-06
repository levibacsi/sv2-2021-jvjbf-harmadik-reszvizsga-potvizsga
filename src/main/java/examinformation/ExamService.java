package examinformation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExamService {
    private int theoryMax;
    private int practiceMax;
    private Map<String, ExamResult> results = new TreeMap<>();


    public void readFromFIle(Path path){
        try {List<String> input = Files.readAllLines(path);
            maxPointsFinder(input);
            mapper(input);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file: " + path);
        }
    }

    private void maxPointsFinder(List<String> input){
        String[] firstLine = input.get(0).split(" ");
        theoryMax = Integer.parseInt(firstLine[0]);
        practiceMax = Integer.parseInt(firstLine[1]);
    }

    private void mapper(List<String> input){
        for (int i = 1; i < input.size(); i++){
            String[] temp = input.get(i).split(";");
            String name = temp[0];
            String[] points = temp[1].split(" ");
            int theory = Integer.parseInt(points[0]);
            int practice = Integer.parseInt(points[1]);
            results.putIfAbsent(name, new ExamResult(theory, practice));
        }
    }

    public int getTheoryMax(){
        return theoryMax;
    }

    public int getPracticeMax(){
        return practiceMax;
    }

    public Map<String, ExamResult> getResults() {
        return results;
    }

    public List<String> findPeopleFailed(){
        return results.entrySet().stream()
                .filter(e->e.getValue().getPractice() < practiceMax * .51 ||
                        e.getValue().getTheory() < theoryMax * .51)
                .map(Map.Entry::getKey)
                .toList();
    }

    public String findBestPerson(){
        return results.entrySet().stream()
                .filter(e->(e.getValue().getPractice() > practiceMax * .51) && (e.getValue().getTheory() > theoryMax * .51))
                .max(Comparator.comparingInt(e->e.getValue().getTheory() + e.getValue().getPractice()))
                .orElseThrow(()-> new IllegalArgumentException("No students"))
                .getKey();
    }
}
