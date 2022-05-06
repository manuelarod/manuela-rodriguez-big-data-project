javac -classpath `yarn classpath` -d . MonthlyAvgDailyTripsMapper.java
javac -classpath `yarn classpath` -d . MonthlyAvgDailyTripsReducer.java
javac -classpath `yarn classpath`:. -d . MonthlyAvgDailyTrips.java
jar -cvf monthlyAvgDailyTrips.jar *.class
hadoop jar monthlyAvgDailyTrips.jar MonthlyAvgDailyTrips citibike_data/daily_trips_count_output/part-r-00000 /user/mr5340/citibike_data/monthly_avg_daily_trips_output
