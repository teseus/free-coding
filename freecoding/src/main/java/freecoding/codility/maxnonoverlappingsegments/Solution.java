package freecoding.codility.maxnonoverlappingsegments;

class Solution {
    public int solution(int[] A, int[] B) {

        if(A.length <= 1) return A.length;

        int count = 1;
        int lastEnd = B[0];

        for (int i = 1; i < A.length; i++) {
            if(A[i] > lastEnd){
                lastEnd = B[i];
                count++;
            }
        }

        return count;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int [] A = {1,3 ,7 ,9,9};
//        int [] B = {5,6 ,8 ,9,10};
//        System.out.println("answer:" + solution.solution(A, B));
        int [] C = {1,2, 3,4, 5, 10};
        int [] D = {2,3, 4,5, 6, 10};
        System.out.println("answer:" + solution.solution(C, D));
//        int [] E = {1};
//        int [] F = {5};
//        System.out.println("answer:" + solution.solution(E, F));
//        int [] G = {5,7,9, 11,11};
//        int [] H = {7,9,10, 11,12};
//        System.out.println("answer:" + solution.solution(G, H));
//        int [] I = {1, 5};
//        int [] J = {2, 7};
//        System.out.println("answer:" + solution.solution(I, J));
//        int [] K = {1,2,3,4};
//        int [] L = {2,3,4,7};
//        System.out.println("answer:" + solution.solution(K, L));
    }
}
