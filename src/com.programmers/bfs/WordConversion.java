package com.programmers.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    String word;
    int count;

    Node(String word, int count) {
        this.word = word;
        this.count = count;
    }
}

public class WordConversion {
    //https://school.programmers.co.kr/learn/courses/30/lessons/43163

    /**
     * [조건]
     * 1. 각 단어는 알파벳 소문자로만 이루어져 있습니다.
     * 2. 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
     * 3. words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
     * 4. begin과 target은 같지 않습니다.
     * 5. 변환할 수 없는 경우에는 0를 return 합니다.
     *
     * [결과깂]
     * 두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때,
     * 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return
     *
     * EX)
     * begin  : "hit"
     * target : "cog"
     * words  : ["hot", "dot", "dog", "lot", "log", "cog"]
     * return : 4
     *
     */

    static boolean[] visited; // 방문 여부 체크
    static int answer;

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
//        String[] words = {"hot", "dot", "dog", "lot", "log"};
        System.out.println("sol : " + solution(begin, target, words));
    }

    public static int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        answer = words.length; // 단어 변환이 불가능한 경우를 생각하여, words 배열 크기만큼 초기화

        //words 배열 안에 target이 없는 경우 처리
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }

        return bfs(begin, target, words);
    }

    public static int bfs(String begin, String target, String[] words) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(begin, 0));

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (cur.word.equals(target)) {
                return cur.count;
            }

            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue;

                if (wordCheck(cur.word, words[i])) {
                    que.add(new Node(words[i], cur.count + 1));
                    visited[i] = true;
                }
            }
        }

        return 0;
    }

    public static boolean wordCheck(String begin, String target) {
        int count = 0;
        for (int j = 0; j < begin.length(); j++) {
            if (begin.charAt(j) != target.charAt(j)) count++;
        }
        return count == 1 ? true : false;
    }
}