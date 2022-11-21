package main.java.com.ppomar.socks;

import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public int solution(int capacity, int[] clean, int[] dirty) {
        Map<Integer, Integer> cleanMap = getMapFrequencies(clean);
        Map<Integer, Integer> dirtyMap = getMapFrequencies(dirty);

        int parSocks = getPairsFromMap(cleanMap);
        parSocks += searchDirtyPair(cleanMap, dirtyMap, capacity);
        return parSocks;
    }

    private int getPairsFromMap(Map<Integer, Integer> map) {
        int pairs = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pairs += countPairs(entry.getValue());
        }
        return pairs;
    }

    private int searchDirtyPair(Map<Integer, Integer> cleanMap, Map<Integer, Integer> dirtyMap, int capacity) {
        // una vez hecha la pareja sigue con el for
        // aunque no queden mas parejas por hacer sigue con el for
        // breaks?
        int pairs = 0;
        for (Map.Entry<Integer, Integer> colorClean : cleanMap.entrySet()) {
            if (hasNotPair(colorClean.getValue()) && capacity > 0) {
                for (Map.Entry<Integer, Integer> colorDirty : dirtyMap.entrySet()) {
                    if (colorDirty.getKey().equals(colorClean.getKey()) && colorDirty.getValue() > 0) {
                        pairs++;
                        capacity--;
                        colorDirty.setValue(colorDirty.getValue() - 1);
                    }
                }
            }
        }
        pairs += getPairsFromDirty(dirtyMap, capacity);
        return pairs;
    }

    private int countPairs(Integer value) {
        return value / 2;
    }

    private int getPairsFromDirty(Map<Integer, Integer> map, int k) {
        return Math.min(k, getPairsFromMap(map) * 2) / 2;
    }

    private boolean hasNotPair(int value) {
        return value % 2 != 0;
    }

    private Map<Integer, Integer> getMapFrequencies(int[] list) {
        Map<Integer, Integer> groups = new TreeMap<>();
        for (int type : list) {
            groups.merge(type, 1, Integer::sum);
        }
        return groups;
    }

}
