package nextsteptdd.subwaymap.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {

    public static final String ERROR_DUPLICATION_STATION = "이미 동일한 이름의 역이 있습니다.";

    private static final List<Station> stations = new ArrayList<>();

    static {
        stations.add(new Station("교대역"));
        stations.add(new Station("강남역"));
        stations.add(new Station("역삼역"));
        stations.add(new Station("남부터미널역"));
        stations.add(new Station("양재역"));
        stations.add(new Station("양재시민의숲역"));
        stations.add(new Station("매봉역"));
    }

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        validateAlreadyStationExist(station);
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    private static void validateAlreadyStationExist(Station station) {
        if (hasStation(station)) {
            throw new RuntimeException(ERROR_DUPLICATION_STATION);
        }
    }

    private static boolean hasStation(Station station) {
        return stations.stream().anyMatch(e -> e.getName().equals(station.getName()));
    }

}