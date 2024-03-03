package com.programmers.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TravelRoute {
    //https://school.programmers.co.kr/learn/courses/30/lessons/43164

    /**
     * [조건]
     * 1. 항상 "ICN" 공항에서 출발
     * 2. 모든 공항은 알파벳 대문자 3글자로 이루어짐
     * 3. 주어진 공항 수는 3개 이상 10,000개 이하
     * 4. tickets의 각 행 [a,b]는 a공항에서 b공항으로 가는 항공권이 있다는 의미
     * 5. 주어진 항공권은 모두 사용해야함
     * 6. 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로 리턴
     * 7. 모든 도시를 방문할 수 없는 경우는 존재하지 않음
     *
     * [결과값]
     * tickets : [["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]
     * result  : ["ICN", "JFK", "HND", "IAD"]
     *
     * 항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때,
     * 방문하는 공항 경로를 배열에 담아 리턴
     */

    static boolean[] visited;
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
//        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        System.out.println(Arrays.toString(solution(tickets)));
    }

    public static String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        dfs(tickets, "ICN", "ICN", 0);
        Collections.sort(list);

        return list.get(0).split(" ");
    }

    public static void dfs(String[][] tickets, String current, String sequence, int depth) {
        if (depth == tickets.length) {
            list.add(sequence);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (visited[i]) continue;

            if (tickets[i][0].equals(current)) {
                visited[i] = true;
                dfs(tickets, tickets[i][1], sequence + " " + tickets[i][1], depth+1);
                visited[i] = false;
            }
        }
    }

}