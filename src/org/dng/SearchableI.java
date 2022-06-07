package org.dng;

import java.util.List;

@FunctionalInterface
public interface SearchableI {
    List<Car> searchByCost(List<Car> list, int minCost, int maxCost);
}
