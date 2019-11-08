package com.example.demo.codility.testingnow;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Solution {
    public int solution(Point2D[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                for (int k = j+1; k < A.length; k++) {
                    System.out.println("i=" + i + "j=" + j + "k=" +k);
                    if(isCollinear(A[i].x, A[i].y, A[j].x, A[j].y, A[k].x, A[k].y)){
                        count++;
                    }
                }
            }
        }

        System.out.println("result = " + count);
        return count;
    }

    public static boolean isCollinear(int x, int y, int x1, int y1, int x2, int y2){
        return 0 == ((y2 - y1) * x + (x1 - x2) * y + (x2 * y1 - x1 * y2));
    }


    public static boolean isCollinear(List<Point2D> points){
        //return (y2 - y1) * x + (x1 - x2) * y + (x2 * y1 - x1 * y2);
        return 0 == ((points.get(2).y - points.get(1).y) * points.get(0).x
                + (points.get(1).x - points.get(2).x) * points.get(0).y
                + (points.get(2).x * points.get(1).y - points.get(1).x * points.get(2).y));
    }


    public static class Point2D{
        public int x;
        public int y;

        public Point2D(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        Point2D [] arr = {new Point2D(0,0), new Point2D(1,1), new Point2D(2,2), new Point2D(3,3),
                new Point2D(3,2),new Point2D(4,2),new Point2D(5,1)};
        log.info("the answer : {}", solution.solution(arr));
    }
}
