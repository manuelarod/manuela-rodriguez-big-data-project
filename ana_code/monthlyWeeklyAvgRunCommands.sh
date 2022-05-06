javac -classpath `yarn classpath` -d . MonthlyAvgWeeklyTripsMapper.java
javac -classpath `yarn classpath` -d . MonthlyAvgWeeklyTripsReducer.java
javac -classpath `yarn classpath`:. -d . MonthlyAvgWeeklyTrips.java
jar -cvf monthlyAvgWeeklyTrips.jar *.class
hadoop jar monthlyAvgWeeklyTrips.jar MonthlyAvgWeeklyTrips citibike_data/weekly_trips_count_output/part-r-00000 /user/mr5340/citibike_data/monthly_avg_weekly_trips_output
