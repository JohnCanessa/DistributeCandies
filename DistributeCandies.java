import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 
 */
public class DistributeCandies {


    
    /**
     * Return the maximum number of different types of candies 
     * she can eat if she only eats n / 2 of them.
     * Using priority queue.
     * 
     * 206 / 206 test cases passed.
     * Status: Accepted
     * Runtime: 248 ms
     * Memory Usage: 113.9 MB
     */
    static public int distributeCandies0(int[] candyType) {

        // **** initialization ****
        int nHalf                   = candyType.length / 2;
        PriorityQueue<Integer> pq   = new PriorityQueue<>();

        // **** populate priority queue ****
        for (int c : candyType) pq.add(c);

        // **** remove elements from priority queue counting different flavors ****
        int diffCnt = 1;
        int prev    = pq.remove();
        while (!pq.isEmpty()) {

            // **** ****
            int c = pq.remove();

            // **** ****
            if (c != prev) {
                prev = c;
                diffCnt++;
            }

            // **** ****
            if (diffCnt >= nHalf) return nHalf;
        }

        // **** return maximum number of different candies ****
        return diffCnt;
    }


    /**
     * Return the maximum number of different types of candies 
     * she can eat if she only eats n / 2 of them.
     * Using a hashmap.
     * 
     * 206 / 206 test cases passed.
     * Status: Accepted
     * Runtime: 69 ms
     * Memory Usage: 114.7 MB
     */
    static public int distributeCandies1(int[] candyType) {

        // ***** initialization ****
        int nHalf                       = candyType.length / 2;
        HashMap<Integer, Integer> cts   = new HashMap<>();

        // **** populate candy types hashmap ****
        for (int c : candyType) {
            Integer cnt = cts.putIfAbsent(c, 1);
            if (cnt != null) cts.put(c, ++cnt);
        }

        // **** count of different candies ****
        int diffCnt = cts.size();

        // **** return maximum number of different candies ****
        if (diffCnt >= nHalf) return nHalf;
        else return diffCnt;
    }


    /**
     * Return the maximum number of different types of candies 
     * she can eat if she only eats n / 2 of them.
     * Sort candy type array.
     * 
     * Execution: O(n * log(n)) - Space: O(1)
     * 
     * 206 / 206 test cases passed.
     * Status: Accepted
     * Runtime: 46 ms
     * Memory Usage: 99.4 MB
     */
    static public int distributeCandies2(int[] candyType) {

        // **** initialization ****
        int n       = candyType.length;
        int nHalf   = n / 2;
        int diffCnt = 1;

        // **** sort candy type array - O(n * log(n) ****
        Arrays.sort(candyType);

        // **** count different candy types - O(n) ****
        int type = candyType[0];
        for (int i = 1; i < n; i++) {
            if (candyType[i] != type) {

                // **** ****
                diffCnt++;

                // **** ****
                type = candyType[i];

                 // **** ****
                 if (diffCnt >= nHalf) return nHalf;           
            }
        }

        // **** return maximum number of different candies ****
        return diffCnt;
    }


    /**
     * Return the maximum number of different types of candies 
     * she can eat if she only eats n / 2 of them.
     * Using hashset.
     * 
     * 206 / 206 test cases passed.
     * Status: Accepted
     * Runtime: 32 ms
     * Memory Usage: 41 MB
     */
    static public int distributeCandies(int[] candyType) {

        // ***** initialization ****
        HashSet<Integer> cts = new HashSet<>();

        // **** populate candy types hashset ****
        for (int c : candyType) cts.add(c);

        // **** return maximum number of different candies ****
        // return (cts.size() < candyType.length / 2) ? cts.size() :  candyType.length / 2;
        return Math.min(cts.size(), candyType.length / 2);
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read candy type int[] ****
        int[] candyType = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< candyType: " + Arrays.toString(candyType));

        // **** call function of interest and display result ****
        System.out.println("main <<< output: " + distributeCandies0(candyType));

        // **** call function of interest and display result ****
        System.out.println("main <<< output: " + distributeCandies1(candyType));

        // **** call function of interest and display result ****
        System.out.println("main <<< output: " + distributeCandies2(candyType));

        // **** call function of interest and display result ****
        System.out.println("main <<< output: " + distributeCandies(candyType));
    }
}