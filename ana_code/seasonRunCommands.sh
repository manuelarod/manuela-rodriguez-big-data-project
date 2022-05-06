javac -classpath `yarn classpath` -d . SeasonDailyTripsMapper.java
javac -classpath `yarn classpath` -d . SeasonDailyTripsReducer.java
javac -classpath `yarn classpath`:. -d . SeasonDailyTrips.java
jar -cvf seasonDailyTrips.jar *.class
hadoop jar seasonDailyTrips.jar SeasonDailyTrips citibike_data/daily_trips_count_output/part-r-00000 /user/mr5340/citibike_data/season_daily_trips_output
