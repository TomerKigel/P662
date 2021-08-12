import java.text.DecimalFormat;
import java.util.*;

public class source {
	public static void main(String[] args) {
	Vector<Integer> vec = new Vector<Integer>();
	Vector<Vector<Integer>> pithagorian = new Vector<Vector<Integer>>();
	long grid[][] = new long[10001][10001];
	
	int i = 2;
	int res = 1;
	while(res <= 14143)
	{
		vec.add(res);
		res = fobi(i);
		i++;
	}
	vec.remove(0);
	for (int j = 0; j < vec.size(); j++) {
		for (int x = 1; x < vec.elementAt(j); x++) {
			for (int y = 1; y < x; y++) {
				if(x*x + y*y == vec.elementAt(j)*vec.elementAt(j))
				{
					Vector<Integer> tuple = new Vector<Integer>();
					tuple.add(vec.elementAt(j));
					tuple.add(x);
					tuple.add(y);
					pithagorian.add(tuple);
				}
			}
		}
	
	}
	pithagorian.remove(pithagorian.size()-1);
	pithagorian.remove(pithagorian.size()-1);
	pithagorian.remove(pithagorian.size()-1);
	for(int y = 10000;y >= 0;y--){
		for(int j = 10000; j >=0;j--)
		{
			grid[y][j] = gridsum(grid,y,j,vec,10000,10000,pithagorian);
		}
		System.out.println(y);
	}
	System.out.println(grid[10000][10000]);
	System.out.println(grid[0][0]);
	}
	
	public static int fobi(int i)
	{
		if(i == 1 || i == 2)
			return 1;
		return fobi(i - 1) + fobi(i - 2);
	}
	
	public static long gridsum(long grid[][],int i,int j,Vector<Integer> vec,int target_x,int target_y,Vector<Vector<Integer>> p)
	{
		long res = 0;
		if(i > target_y || j > target_x || (j == target_x && i == target_y))
			return 0;
		for(int y = 0; y < vec.size();y++)
		{
			
			if((vec.elementAt(y) + j == target_x && i == target_y) || (j == target_x && vec.elementAt(y) + i == target_y))
			{
				res++;
				y++;
			}
			if(vec.elementAt(y) + j <= target_x)
				res = (res+grid[i][j+vec.elementAt(y)]) % 1000000007;
			if(vec.elementAt(y) + i <= target_y)
				res = (res+grid[i+vec.elementAt(y)][j]) % 1000000007;
			
			for (Iterator iterator = p.iterator(); iterator.hasNext();) {
				Vector<Integer> vector = (Vector<Integer>) iterator.next();
				if(vec.elementAt(y) == vector.elementAt(0))
				{
					if(j+vector.elementAt(1) == target_x && i+vector.elementAt(2) == target_y ){
						res++;
					}
					else if(j+vector.elementAt(1) <= target_x && i+vector.elementAt(2) <= target_y ){
						res = (res+grid[i+vector.elementAt(2)][j+vector.elementAt(1)]) % 1000000007;
					}
					
					if(j+vector.elementAt(2) == target_x && i+vector.elementAt(1) == target_y ){
						res++;
					}
					else if((j+vector.elementAt(2) <= target_x && i+vector.elementAt(1) <= target_y)){
						res = (res+grid[i+vector.elementAt(1)][j+vector.elementAt(2)]) % 1000000007;
					}
				}
				
			}
		}
		return res % 1000000007;
	}
}
