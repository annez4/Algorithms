import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MeetingScheduling {
	public static int xIndex(int m, int d) {
		//  months to days, plus date
		if (m <= 7 && m > 1) {
			int rd1 = (m - 1) / 2;  // round down
			int rd2 = m / 2; // round down
			return rd1 * 30 - 2 + rd2 * 31 + d - 1;	
		} else if (m == 1){
			return d - 1;
		} else {
			m = m - 7;
			int rd1 = (m - 1) / 2;  // round down
			int rd2 = m / 2; // round down
			return 3 * 30 - 2 + 4 * 31 + rd1 * 30 + rd2 * 31 + d - 1;  
		}		
	}
	
	public static int yIndex(int hr, int min) {
		return (hr - 9) * 4 + (min / 15);	
	}
	
	public static String xToDate(String x) {
		int numberOfDays = Integer.parseInt(x) + 1;
		
		int month = numberOfDays / 30 + 1;  
		int day = numberOfDays % 30;
		
		if (month <= 7 && month >= 3 ) {
			int temp = month / 2;  // number of months with 31 days
			day = day + 2 - temp;
			
			return "" + month + day;
		} else if (month > 7) {
			int temp = (month - 7) / 2;
			day = day + 2 - 4 - temp;
		} else if (month == 2){
			day -= 1;
		} else {
		}
		return month + " " + day;
	}
	
	public static String yToTime(String y) {
		int hour = Integer.parseInt(y) / 4 + 9;
		int min = Integer.parseInt(y) % 4 * 15;
		
		if (Integer.parseInt(y) / 4 == 0) {
			if (min == 0) {
				return "0" + hour + min + "0";
			} else {
				return "0" + hour + min;
			}
		} else {
			if (min == 0) {
				return "" + hour + min + "0";
			} else {
				return "" + hour + min;
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		// initial calendar
		byte[][] calendar = new byte[33][365];
		
		String thisWeekday;
		int thisMonth;
		int thisDate;
		
		int numberOfMeetings;
		int duration;
	
		// get input
		File file = new File("input.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		
		// first line, get current date
		line = bufferedReader.readLine();
		String[] firstLine = line.split(" "); // weekday month date
		
		thisWeekday = firstLine[0];
		thisMonth = Integer.parseInt(firstLine[1]);
		thisDate = Integer.parseInt(firstLine[2]);
		
		// second line, get target
		line = bufferedReader.readLine();
		String[] secondLine = line.split(" ");  // numbers duration
		
		numberOfMeetings = Integer.parseInt(secondLine[0]);
		duration = Integer.parseInt(secondLine[1]);
		
		int numberOfMeetingsCopy = numberOfMeetings;
		
		while (true) {
			int month;
			int date;
			int startHr;
			int startMin;
			int endHr;
			int endMin;
			
			line = bufferedReader.readLine();
			
			if (line == null) {	// end of the file
				break;
			} else {
				String[] subStr = line.split(" ");
				
				if(subStr.length <= 4) {  // ignore done and names
					continue;
				}
				
				month = Integer.parseInt(subStr[1]);
				date = Integer.parseInt(subStr[2]);
				String start = subStr[3];
				String end = subStr[4];
				
				startHr = Integer.parseInt(start.substring(0, 2));
				startMin = Integer.parseInt(start.substring(2));
				endHr = Integer.parseInt(end.substring(0, 2));
				endMin = Integer.parseInt(end.substring(2));
			}
			
			// convert into index
			int x = xIndex(month, date);
			int y1 = yIndex(startHr, startMin);
			int y2 = yIndex(endHr, endMin) - 1;
			
			// set occupied time chunk to 1;
			for (int a = y1; a <= y2; a ++) {
				calendar[a][x] = 1;
			}			
		}
			
		fileReader.close();
		
		// search for vacancy
		int weekday;
		if (thisWeekday.equals("M")) {
			weekday = 1;
		} else if (thisWeekday.equals("T")) {
			weekday = 2;
		}  else if (thisWeekday.equals("W")) {
			weekday = 3;
		}  else if (thisWeekday.equals("R")) {
			weekday = 4;
		}  else {
			weekday = 5;
		}
		
		int startXIndex = xIndex(thisMonth, thisDate);
		int endSearchDate = startXIndex;
		System.out.println(startXIndex);
		duration = duration / 15;  // number of time chunk needed
		String[] vacancyIndex = new String[numberOfMeetings];
		boolean foundOne = false;
		
		while (numberOfMeetings != 0) {
			for (int i = startXIndex; i < 365; i ++, weekday ++) {
				if (foundOne) {
					foundOne = false;
					break;
				}
				
				if (weekday == 6 || weekday == 7) {  // exclude Saturday and Sunday
					if (weekday == 7) {
						weekday = 0;
					}
					continue;
				}

				for (int j = 0; j < 32; j ++) {
					if (calendar[j][i] == 1) {  
						continue;
					} else if ( j + duration >= 31) {
						break;  // not enough time chunk left in the day			
					} else {
						int k = duration - 1;
						calendar[j][i] = 1;
						
						while(k != 0) {
							k--;
							j++;
							if (calendar[j][i] == 1) {
								break;
							}
							calendar[j][i] = 1;
						}
						
						if (k == 0) {
							vacancyIndex[numberOfMeetings - 1] = weekday + " " + i + " " + (j - duration + 1);  // add vacancy to the last index of the array
							System.out.println(vacancyIndex[numberOfMeetings - 1]);
							numberOfMeetings --;
							startXIndex = i;
							foundOne = true;
							weekday --;
							break;
						}					
					}
				}
				if (i + 1 == 365) {  // next year starts
					i = -1;
				} else if ( i + 1 == endSearchDate) {	// back to the starting point
					break;
				}
				
			}			
		}
		
		String[] vacancy = new String[numberOfMeetingsCopy];
		
		for (int a = numberOfMeetingsCopy; a > 0; a --) {
			String[] subStr = vacancyIndex[a - 1].split(" ");
			
			String weekdayStr;
			if (Integer.parseInt(subStr[0]) == 1) {
				weekdayStr = "M";
			} else if (Integer.parseInt(subStr[0])  == 2) {
				weekdayStr = "T";
			}  else if (Integer.parseInt(subStr[0])  == 3) {
				weekdayStr = "W";
			}  else if (Integer.parseInt(subStr[0])  == 4) {
				weekdayStr = "R";
			}  else {
				weekdayStr = "F";
			}
			
			String vacDate = xToDate(subStr[1]);
			String vacTime = yToTime(subStr[2]);
			
			vacancy[numberOfMeetingsCopy - a] = weekdayStr + " " + vacDate + " " + vacTime;
			System.out.println(vacancy[numberOfMeetingsCopy - a]);
		}	
	}
}