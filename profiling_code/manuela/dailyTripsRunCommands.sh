javac -classpath `yarn classpath` -d . DailyTripsCountMapper.java
javac -classpath `yarn classpath` -d . DailyTripsCountReducer.java
javac -classpath `yarn classpath`:. -d . DailyTripsCount.java
jar -cvf dailyTripsCount.jar *.class
hadoop jar dailyTripsCount.jar DailyTripsCount citibike_data/output/part-r-00000 /user/mr5340/citibike_data/daily_trips_count_output
