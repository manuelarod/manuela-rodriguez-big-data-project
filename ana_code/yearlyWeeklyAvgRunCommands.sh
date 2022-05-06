javac -classpath `yarn classpath` -d . YearlyAvgWeeklyTripsMapper.java
javac -classpath `yarn classpath` -d . YearlyAvgWeeklyTripsReducer.java
javac -classpath `yarn classpath`:. -d . YearlyAvgWeeklyTrips.java
jar -cvf yearlyAvgWeeklyTrips.jar *.class
hadoop jar yearlyAvgWeeklyTrips.jar YearlyAvgWeeklyTrips citibike_data/weekly_trips_count_output/part-r-00000 /user/mr5340/citibike_data/yearly_avg_weekly_trips_output
