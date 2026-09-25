package com.demo.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Q3568_MinimumMovesToCleanTheClassroom {

    static class State{
        int r, c, energy, mask, moves;

        State(int r, int c, int energy, int mask, int moves){
            this.r=r;
            this.c=c;
            this.energy=energy;
            this.mask=mask;
            this.moves=moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m= classroom.length;;
        int n=classroom[0].length();

        int startR=0, startC=0;
        int totalL=0;

        int[][] id = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(classroom[i].charAt(j)=='S'){
                    startR=i;
                    startC=j;
                }

                if(classroom[i].charAt(j)=='L'){
                    id[i][j]=totalL++;
                }
            }
        }


        if(totalL==0) return 0;
        int allCollected=(1<<totalL) -1; // 1111

        Queue<State> q = new LinkedList<>();
        boolean [][][][] visited = new boolean[m][n][energy+1][1<<totalL];

        q.offer(new State(startR, startC, energy, 0, 0));
        visited[startR][startC][energy][0]=true;

        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        while(!q.isEmpty()){
            State curr = q.poll();
            if(curr.mask==allCollected) return curr.moves;
            if(curr.energy==0) continue;

            for(int x=0; x<4; x++){
                int nr = curr.r + dr[x];
                int nc = curr.c + dc[x];

                if(nr<0 || nr>=m || nc<0 || nc>=n) continue; // invalid coordinates

                if(classroom[nr].charAt(nc)=='X') continue; // wall

                int newEnergy=curr.energy-1;
                int newMask=curr.mask;

                if(classroom[nr].charAt(nc)=='R') newEnergy=energy; // reSet the energy
                if(classroom[nr].charAt(nc)=='L') {
                    int trashId = id[nr][nc];
                    newMask = newMask | (1 << trashId);
                }

                if(!visited[nr][nc][newEnergy][newMask]){
                    visited[nr][nc][newEnergy][newMask]=true;
                    q.offer(new State(nr,nc,newEnergy, newMask, curr.moves+1));
                }
            }

        }

        return -1;

    }
}
