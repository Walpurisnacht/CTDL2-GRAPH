package vn.edu.tdt.it.dsa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class DeliveringMap {
	public static final int MAX_EDGE = 100;

	private int[][] undirectedGraph = new int[MAX_EDGE][MAX_EDGE];
	private int[][] directedGraph = new int[MAX_EDGE][MAX_EDGE];
	private int sumEdgesVertices = 0;
	private ArrayList<Integer> path = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

	public DeliveringMap(File file) throws IOException{
		//sinh vien viet ma o day
		Scanner sc = new Scanner(file);
		List<String> list = new ArrayList<>();
		Set<Integer> vertices = new HashSet<>();
		for (int i = 0; i < MAX_EDGE; i++) {
			Arrays.fill(undirectedGraph[i], Integer.MAX_VALUE);
			Arrays.fill(directedGraph[i], Integer.MAX_VALUE);
		}

		while (sc.hasNext()) {
			list.add(sc.next());
		}

		for (int i=0; i < list.size(); i ++) {
			int weight = Integer.parseInt(list.get(i).substring(2,5));
			int src = Integer.parseInt(list.get(i).substring(0,2));
			int dest = Integer.parseInt(list.get(i).substring(5,7));

			undirectedGraph[src][dest] = weight;
			undirectedGraph[dest][src] = weight;
			directedGraph[src][dest] = weight;

			vertices.add(src);
			vertices.add(dest);
			sumEdgesVertices += weight;
		}
		sumEdgesVertices += vertices.size();
	}

	
	public int calculate(int level, boolean rushHour){
		int res = 0;
		//sinh vien viet ma o day
		return res;
	}

	private int case_1 (int level) {
		return sumEdgesVertices + level;
	}

	private void DFSUtil (int src, int dest, int graph[][], boolean visited[]) {
		visited[src] = true;
		path.add(src);
		if (src == dest) {
			paths.add(new ArrayList<Integer>(path));
			path.remove(path.size()-1);
			//return;
		}
		else {
			//System.out.println(src + " ");	//debug
			for (int i = 0; i < MAX_EDGE; i++) {
				if (graph[src][i] != Integer.MAX_VALUE) {
					if (!visited[i]) {
						DFSUtil(i, dest, graph, visited);
					}
				}
			}
			path.remove(path.size()-1);
		}
		//path.pop();
		visited[src] = false;

	}

	private void DFS (int src, int dest, int graph[][]) {
		boolean visited[] = new boolean[MAX_EDGE];
		DFSUtil(src, dest, graph, visited);
	}

	
	public static void main (String[] args){
		try{
			DeliveringMap map = new DeliveringMap(new File("map.txt"));
			//System.out.println(map.calculate(3, false));		//default do not touch
			//System.out.println(map.case_1(1));	//debug
			map.DFS(1, 7, map.undirectedGraph);
			System.out.println(map.path);
			System.out.println(map.paths);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
