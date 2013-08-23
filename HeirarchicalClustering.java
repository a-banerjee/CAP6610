package edu.ufl.cise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HeirarchicalClustering {

	private HashMap<Integer, Integer> firstLevelclusterSetMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> secondLevelclusterSetMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> thirdLevelclusterSetMap = new HashMap<Integer, Integer>();
	int firstLevelClusterKey = 1;
	int secondLevelClusterKey = 1;
	int thirdLevelClusterKey = 1;
	//first level mood
	public void firstLevelCluster(String user) throws FileNotFoundException{


			Scanner scanner = new Scanner(new File(user+".csv"));
			scanner.useDelimiter(",");
			HashMap<Integer, String> songMoodVector = new HashMap<Integer, String>();
			while(scanner.hasNext()){
				int songID = scanner.nextInt();
				int count = 14;
				String moodVal = null;
				while(scanner.hasNext() && count != 0){
					moodVal += scanner.next().trim() + ",";
					count--;
				}

				for (int key : songMoodVector.keySet()) {
					if(songMoodVector.get(key).equals(moodVal)){

						firstLevelclusterSetMap.put(songID, firstLevelclusterSetMap.get(key));
					}
				}
				firstLevelclusterSetMap.put(songID, firstLevelClusterKey++);
				songMoodVector.put(songID, moodVal);

				if(scanner.hasNextLine()){
					scanner.nextLine();
				}
				else break;
			}


			int [] clusterSize = new int[firstLevelClusterKey];
			//initialze all to zeros
			for(int j = 0 ; j < clusterSize.length ; j++){
				clusterSize[j] = 0;
			}

			int i = 1;
			while ( i <= firstLevelClusterKey){
				for (int keys : firstLevelclusterSetMap.keySet()) {
					if(firstLevelclusterSetMap.get(keys) == i){
						clusterSize[i]++;
					}
				}
				i++;
			}
	}

	//culture based
	public void secondLevelCluster(String user) throws FileNotFoundException{

		Scanner scanner = new Scanner(new File(user+".csv"));
		scanner.useDelimiter(",");
		HashMap<Integer, String> songcultureVector = new HashMap<Integer, String>();

		HashMap<Integer, ArrayList<Integer>> groupedClusterMap = new HashMap<Integer, ArrayList<Integer>>();
		for (int songID : firstLevelclusterSetMap.keySet()) {
			ArrayList<Integer> songIDs = groupedClusterMap.get(firstLevelclusterSetMap.get(songID));
			if(songIDs == null){
				songIDs = new ArrayList<Integer>();
			}
			songIDs.add(songID);
			groupedClusterMap.put(firstLevelclusterSetMap.get(songID), songIDs);
		}

		for(int i = 1 ; i <= firstLevelClusterKey ; i++){
			ArrayList<Integer> songIDs = groupedClusterMap.get(i);
			for (Integer songID : songIDs) {
				while(scanner.hasNext()){
					int sid = Integer.parseInt(scanner.next().trim());
					if(sid == songID){
						int skipCount = 14;
						while(scanner.hasNext() && skipCount != 0){
							scanner.next();
							skipCount--;
						}

						int count = 18;
						String cultureVal = null;
						while(scanner.hasNext() && count != 0){
							cultureVal += scanner.next().trim() + ",";
							count--;
						}

						for (int key : songcultureVector.keySet()) {
							if(songcultureVector.get(key).equals(cultureVal)){
								secondLevelclusterSetMap.put(songID, secondLevelclusterSetMap.get(key));
							}
						}
						
						secondLevelclusterSetMap.put(songID, secondLevelClusterKey++);
						if(scanner.hasNextLine()){
							scanner.nextLine();
						}
						else break;
					}
				}
			}
		}

	}
	
	public void thirsLevelCluster(String user) throws FileNotFoundException{

			Scanner scanner = new Scanner(new File(user+".csv"));
			scanner.useDelimiter(",");
			HashMap<Integer, String> songcultureVector = new HashMap<Integer, String>();

			HashMap<Integer, ArrayList<Integer>> groupedClusterMap = new HashMap<Integer, ArrayList<Integer>>();
			for (int songID : secondLevelclusterSetMap.keySet()) {
				ArrayList<Integer> songIDs = groupedClusterMap.get(secondLevelclusterSetMap.get(songID));
				if(songIDs == null){
					songIDs = new ArrayList<Integer>();
				}
				songIDs.add(songID);
				groupedClusterMap.put(secondLevelclusterSetMap.get(songID), songIDs);
			}

			for(int i = 1 ; i <= firstLevelClusterKey ; i++){
				ArrayList<Integer> songIDs = groupedClusterMap.get(i);
				for (Integer songID : songIDs) {
					while(scanner.hasNext()){
						int sid = scanner.nextInt();
						if(sid == songID){
							int skipCount = 33;
							while(scanner.hasNext() && skipCount != 0){
								scanner.next();
								skipCount--;
							}

							int count = 87;
							String cultureVal = null;
							while(scanner.hasNext() && count != 0){
								cultureVal += scanner.next().trim() + ",";
								count--;
							}

							for (int key : songcultureVector.keySet()) {
								if(songcultureVector.get(key).equals(cultureVal)){
									thirdLevelclusterSetMap.put(songID, thirdLevelclusterSetMap.get(key));
								}
							}
							
							thirdLevelclusterSetMap.put(songID, thirdLevelClusterKey++);
							if(scanner.hasNextLine()){
								scanner.nextLine();
							}
							else break;
						}
					}
				}
			}


			int [] clusterSize = new int[secondLevelClusterKey];
			//initialze all to zeros
			for(int j = 0 ; j < clusterSize.length ; j++){
				clusterSize[j] = 0;
			}

			int i = 1;
			while ( i <= secondLevelClusterKey){
				for (int keys : thirdLevelclusterSetMap.keySet()) {
					if(secondLevelclusterSetMap.get(keys) == i){
						clusterSize[i]++;
					}
//					System.out.println("The song with songID " + thirdLevelclusterSetMap.get(keys) + " is in " + keys + " cluster" );
				}
				i++;
			}
			
			HashMap<Integer, ArrayList<Integer>> finalgroupedClusterMap = new HashMap<Integer, ArrayList<Integer>>();
			for (int songID : thirdLevelclusterSetMap.keySet()) {
				ArrayList<Integer> songIDs = finalgroupedClusterMap.get(thirdLevelclusterSetMap.get(songID));
				if(songIDs == null){
					songIDs = new ArrayList<Integer>();
				}
				songIDs.add(songID);
				finalgroupedClusterMap.put(thirdLevelclusterSetMap.get(songID), songIDs);
			}
			
			for (int clusterkey : finalgroupedClusterMap.keySet()) {
				ArrayList<Integer> listofsongs = finalgroupedClusterMap.get(clusterkey);
				System.out.print(" For cluster " + clusterkey + " songs are ");
				for (Integer song : listofsongs) {
					System.out.print(song);
				}
				System.out.println();
			}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeirarchicalClustering cl = new HeirarchicalClustering();
		try {
			cl.firstLevelCluster("ml1");
			cl.secondLevelCluster("ml1");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
