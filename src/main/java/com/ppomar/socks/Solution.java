package main.java.com.ppomar.socks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Solution {

    public int solution(int k, int[] clean, int[] dirty) {
        int parSocks = getPairsFromOneList(clean);
        clean = getLooseSocks(clean);
        parSocks += getPairsFromCleanAndDirty(k, clean, dirty);
        return parSocks;
    }

    private int getPairsFromOneList(int[] elements) {
        int pairs = 0;
        Map<Integer, Integer> groups = getGroups(elements);
        for (Map.Entry<Integer, Integer> entry : groups.entrySet()) {
            pairs += countPairs(entry.getValue());
        }
        return pairs;
    }

    private int countPairs(Integer value) {
        return value / 2;
    }

    private int getPairsFromCleanAndDirty(int k, int[] clean, int[] dirty) {
        int pairs = 0;
        int index = 0;
        int[] originalDirty = Arrays.copyOf(dirty, dirty.length);
        while (index < originalDirty.length && k > 0) {
            int value = originalDirty[index];
            if (hasPairClean(clean, value)) {
                k--;
                pairs++;
                dirty = removeSock(dirty, value);
                clean = removeSock(clean, value);
            }
            index++;
        }
        pairs += getPairsFromDirty(dirty, k);
        return pairs;
    }

    private int getPairsFromDirty(int[] dirty, int k) {
        return Math.min(k, getPairsFromOneList(dirty) * 2) / 2;
    }

    private boolean hasPairClean(int[] clean, int value) {
        return Arrays.stream(clean).anyMatch(i -> i == value);
    }

    public int[] getLooseSocks(int[] elements) {
        ArrayList<Integer> looseSocks = new ArrayList<>();
        Map<Integer, Integer> groups = getGroups(elements);
        for (Map.Entry<Integer, Integer> entry : groups.entrySet()) {
            if (hasNotPair(entry.getValue())) {
                looseSocks.add(entry.getKey());
            }
        }
        return looseSocks.stream().mapToInt(i -> i).toArray();
    }

    private boolean hasNotPair(int value) {
        return value % 2 != 0;
    }

    public int[] removeSock(int[] arr, int valueToRemove) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        // this list allways contains item
        list.remove(foundFirstValue(arr, valueToRemove));
        return list.stream().mapToInt(i -> i).toArray();
    }

    private int foundFirstValue(int[] arr, int valueToRemove) {
        return Arrays.stream(arr).boxed().collect(Collectors.toList()).indexOf(valueToRemove);
    }

    private Map<Integer, Integer> getGroups(int[] list) {
        Map<Integer, Integer> groups = new TreeMap<>();
        for (int type : list) {
            groups.merge(type, 1, Integer::sum);
        }
        return groups;
    }

}
