//package com.ali.badiee.hw2_2;
//
//import java.util.Arrays;
//
//  class SubsetProblem {
//    static String mainPrefix = "";
//    public static int total_nodes = 0;
//    static StringBuilder results = new StringBuilder();
//
//    // prints subset found
//    static void saveSubset(int[] A, int size) {
//        StringBuilder result = new StringBuilder("{");
//        String prefix = "";
//
//        for (int i = 0; i < size; i++) {
//            result.append(prefix).append(A[i]);
//            prefix = ", ";
//        }
//
//        result.append("}");
//
//        results.append(mainPrefix).append(result);
//        mainPrefix = " - ";
//    }
//
//    // inputs
//    // s		 - set vector
//    // t		 - tuplet vector
//    // s_size	 - set size
//    // t_size	 - tuplet size so far
//    // sum		 - sum so far
//    // ite		 - nodes count
//    // target_sum - sum to be found
//    static void subset_sum(int[] s, int[] t, int s_size, int t_size, int sum, int ite, int target_sum) {
//        total_nodes++;
//
//        if (target_sum == sum) {
//
//            // We found sum
//            saveSubset(t, t_size);
//
//            // constraint check
//            if (ite + 1 < s_size && sum - s[ite] + s[ite + 1] <= target_sum) {
//
//                // Exclude previous added item and consider next candidate
//                subset_sum(s, t, s_size, t_size - 1, sum - s[ite], ite + 1, target_sum);
//            }
//            return;
//
//        } else {
//
//            // constraint check
//            if (ite < s_size && sum + s[ite] <= target_sum) {
//
//                // generate nodes along the breadth
//                for (int i = ite; i < s_size; i++) {
//
//                    t[t_size] = s[i];
//                    if (sum + s[i] <= target_sum) {
//
//                        // consider next level node (along depth)
//                        subset_sum(s, t, s_size, t_size + 1, sum + s[i], i + 1, target_sum);
//                    }
//                }
//            }
//        }
//    }
//
//    // Wrapper that prints subsets that sum to target_sum
//    static void generateSubsets(int[] s, int size, int target_sum) {
//        int[] tuplet_vector = new int[size];
//        int total = 0;
//
//        // sort the set
//        Arrays.sort(s);
//
//        for (int i = 0; i < size; i++) {
//            total = total + s[i];
//        }
//
//        if (s[0] <= target_sum && total >= target_sum) {
//            subset_sum(s, tuplet_vector, size, 0, 0, 0, target_sum);
//        }
//    }
//
//    public static void main(String[] args) {
//        int[] weights = {15, 22, 14, 26, 32, 9, 16, 8};
//        int target = 53;
//        int size = weights.length;
//        generateSubsets(weights, size, target);
//        System.out.println(results);
//    }
//}
//
//// The code is contributed by Gautam goel.
