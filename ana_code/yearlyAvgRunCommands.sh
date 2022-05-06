javac -classpath `yarn classpath` -d . YearlyAvgDailyTripsMapper.java
javac -classpath `yarn classpath` -d . YearlyAvgDailyTripsReducer.java
javac -classpath `yarn classpath`:. -d . YearlyAvgDailyTrips.java
jar -cvf yearlyAvgDailyTrips.jar *.class
hadoop jar yearlyAvgDailyTrips.jar YearlyAvgDailyTrips citibike_data/daily_trips_count_output/part-r-00000 /user/mr5340/citibike_data/yearly_avg_daily_trips_output
