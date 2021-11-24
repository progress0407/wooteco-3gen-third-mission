package nextsteptdd.subwaymap.view;

import nextsteptdd.subwaymap.domain.Line;
import nextsteptdd.subwaymap.domain.LineRepository;
import nextsteptdd.subwaymap.domain.StationRepository;
import nextsteptdd.subwaymap.util.Voider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputViewLine extends OutputView {

    private static final String INPUT_ADD_LINE_NAME = "## 등록할 노선 이름을 입력하세요.";
    private static final String INFO_ADD_LINE = "[INFO] 지하철 노선이 등록되었습니다." + LINE_SEPARATOR;
    private static final String INPUT_DELETE_LINE_NAME = "## 삭제할 노선 이름을 입력하세요.";
    private static final String INFO_DELETE_LINE = "[INFO] 지하철 노선이 삭제되었습니다." + LINE_SEPARATOR;
    private static final String ERROR_DELETE_LINE = "[ERROR] 삭제할 노선이 존재하지 않습니다." + LINE_SEPARATOR;
    private static final String PRINT_LIST_LINE = "## 노선 목록";

    String input;

    public OutputViewLine(String input) {
        this.input = input;
    }

    private static final Map<String, Voider> behaviorMap = new HashMap<>();

    static {
        behaviorMap.put("1", OutputViewLine::addLine);
        behaviorMap.put("2", OutputViewLine::deleteLine);
        behaviorMap.put("3", OutputViewLine::lines);
    }

    @Override
    public String printResult() {
        behaviorMap.get(input).call();
        return input;
    }

    private static void lines() {
        System.out.println(PRINT_LIST_LINE);
        List<Line> lines = LineRepository.lines();
        lines.forEach(e -> System.out.println("[INFO] " + e.getName()));
        System.out.println();
    }

    private static void addLine() {
        System.out.println(INPUT_ADD_LINE_NAME);
        String lineName = scanner.nextLine();
        LineRepository.addLine(new Line(lineName));
        System.out.println(INFO_ADD_LINE);
    }

    private static void deleteLine() {
        System.out.println(INPUT_DELETE_LINE_NAME);
        String lineName = scanner.nextLine();
        boolean deleteLine = LineRepository.deleteLineByName(lineName);
        if (deleteLine) {
            System.out.println(INFO_DELETE_LINE);
            return;
        }
        System.out.println(ERROR_DELETE_LINE);
    }

}