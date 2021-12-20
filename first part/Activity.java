import java.io.*;
import java.util.*;

public class Activity {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Enter the file path, please.");
			return;
		}
	    String filepath = args[0];
	    File inputfile = new File(filepath);
	    Scanner reader = new Scanner(inputfile);
	    int size = reader.nextInt();
	    Act[] list = new Act[size];
	    for(int i = 0; i < size; i++) {
	    	list[i] = new Act(reader.nextInt(), reader.nextInt(), reader.nextInt());
	    }
	    int ans = select(list);
	    String outputfile = filepath.substring(0, filepath.length() - 3);
	    FileWriter myWriter = new FileWriter(outputfile + "_18012538.out");
	    myWriter.write(Integer.toString(ans));
	    myWriter.close();
	    System.out.println("Finished");
	}
	private static class Act{
		private int start, end, weight;
		private Act(int s, int e, int w){
			start = s; end = e; weight = w;
		}
	}
	private static int select(Act[] list) {
        Arrays.sort(list, (a, b)->a.end - b.end);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (Act activity : list) {
            int cur = dp.floorEntry(activity.start).getValue() + activity.weight;
            if (cur > dp.lastEntry().getValue())
                dp.put(activity.end, cur);
        }
        return dp.lastEntry().getValue();
    }

}
